package d_method_invocation_polymorphism_usecase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import d_method_invocation_polymorphism_usecase.database.DatabaseClient;
import d_method_invocation_polymorphism_usecase.http.HttpClient;
import d_method_invocation_polymorphism_usecase.logging.FileLogger;
import d_method_invocation_polymorphism_usecase.udp.UdpClient;

//we will learn execute method using reflection, polymorphism implementation with java reflection
//polymoriphism use case using reflection
//Syntax: Object Method.invoke(Object instance, Object .. args) 

//use case for this is : method name, signature, return type and containing class are decoupled from the logic that controls
//the execution of those methods. 
//It is one of mostly used feature of reflection

//polymorphism : many forms. if different classes extend the same interfaces they can be used together with polymorphism.
//we can group together all this class that extend the same interface and use them. But what if we want to use class together that does not extend interface. 
//let's say i have classes that found entirely different library, may be different methods like HttpClient, DatabaseClient, how can we use them together ? 
//we can do it with reflection.

//What will we learn
//++ how to execute method using Java Reflection
//++ handle exception and get the result from target method
//++ we can group and execute methods of classes that have nothing in common

public class TestClass {

	
	public static void main(String[] args) throws Throwable {
		DatabaseClient databaseClient = new DatabaseClient();
		HttpClient httpClient1 = new HttpClient("1.1.1.1");
		HttpClient httpClient2 = new HttpClient("2.2.2.2");
		FileLogger fileLogger = new FileLogger();
		UdpClient udpClient = new UdpClient();
		
		String requestBody = "request data";
		
		List<Class<?>> methodParameterTypes = Arrays.asList(new Class<?>[] {String.class});
		
		Map<Object, Method> requestExecutors = groupExecutors(Arrays.asList(databaseClient, httpClient1, httpClient2, fileLogger, udpClient), methodParameterTypes);
		
		executeAll(requestExecutors, requestBody);
		
	}
	
	
	
	//This method will execute each selected methods for each request executors coming from groupExecutors().
	private static void executeAll(Map<Object, Method> requestExecutors, Object... arguments) throws Throwable {
		try {
			for(Map.Entry<Object, Method> requestExecutorEntry: requestExecutors.entrySet()) {
				Object requestExecutor = requestExecutorEntry.getKey();
				Method method = requestExecutorEntry.getValue();
				
				Boolean result = (Boolean) method.invoke(requestExecutor, arguments);
				
				//Some of the request executors i.e DatabaseClient return true if it successfully executed. If something happen, lets say false return if so we are aborting executing other methods. we can do something like this.
				if(result != null && result.equals(Boolean.FALSE)) {
					System.out.println("Received negative result. Aborting ...");
					return;
				}
			}
		}
		catch(InvocationTargetException e) {
			//if any of request executor throw an exception then we can catch it and print the exception. we can do things like this.
			e.getTargetException();
		}
	}
	
	//This method will return a map with Object as a key and "a method inside that object that can accept a parameters like methodParameterTypes"
	public static Map<Object, Method> groupExecutors(List<Object> requestExecutors, List<Class<?>> methodParameterTypes){
		Map<Object, Method> instanceToMethod = new HashMap<>();
		
		//loop through all of our executors
		for(Object requestExecutor: requestExecutors) {
			
			Method[] allMethods = requestExecutor.getClass().getDeclaredMethods();
			
			//get a method from executor that matches parameter types
			for(Method method: allMethods) {
				if(Arrays.asList(method.getParameterTypes()).equals(methodParameterTypes)) {
					instanceToMethod.put(requestExecutor, method);
				}
			}
			
		}
		
		return instanceToMethod;
	}
}
