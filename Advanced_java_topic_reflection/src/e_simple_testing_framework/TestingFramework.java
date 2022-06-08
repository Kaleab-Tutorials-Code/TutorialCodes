package e_simple_testing_framework;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestingFramework {
	
	public static void main(String[] args) throws Throwable {
		
		TestingFramework tf = new TestingFramework();
		tf.runTestSuite(PaymentServiceTest.class);
		
		
	}
	
	public void runTestSuite(Class<?> testClass) throws Throwable {
		Object testObjectInstance = testClass.getDeclaredConstructors()[0].newInstance();
		//we only need public method because we told they will be public methods. 
		Method[] methods = testClass.getMethods();
		
		//Execute beforeClass method
		Method beforeClassMethod = findMethodByName(methods, "beforeClass");
		
		//to call static method we don't need to pass instance of an object. 
		if(beforeClassMethod != null) beforeClassMethod.invoke(null);
		
		//Execute setup method
		Method setupTestMethod = findMethodByName(methods, "setupTest");
		setupTestMethod.invoke(testObjectInstance);
		
		//Execute methods start with test
		List<Method> methodsStartedWithSetup = findMethodsByPrefix(methods, "test");
		for(Method method: methodsStartedWithSetup) {
			method.invoke(testObjectInstance);
		}
		
//		//Execute after class
		Method afterClassMethod = findMethodByName(methods, "afterClass");
		afterClassMethod.invoke(null);
	}
	
	private Method findMethodByName(Method[] methods, String name) {
		for(Method method: methods) {
			if(method.getName().equals(name)
					&& method.getReturnType().equals(void.class)
					&& method.getParameterCount() == 0) {
				return method;
			}
				 
		}
		return null;
	}
	
	private List<Method> findMethodsByPrefix(Method[] methods, String prefix){
		
		List<Method> methodsByPrefix = new ArrayList<>();
		
		for(Method method: methods) {
			if(method.getName().startsWith(prefix)
					&& method.getReturnType().equals(void.class)
					&& method.getParameterCount() == 0) methodsByPrefix.add(method);
		}
		return methodsByPrefix;
	}
	
	
	
}
