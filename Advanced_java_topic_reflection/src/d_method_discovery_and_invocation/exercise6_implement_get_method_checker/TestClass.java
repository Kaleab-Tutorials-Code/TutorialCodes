package d_method_discovery_and_invocation.exercise6_implement_get_method_checker;

//In this code implementation you can look at how to get Method object of a class using its name and parameters.
//Class.getDeclaredMethod() : methods on current class, Class.getMethod(): public methods on current class, 
//using this knowledge we implemented a recursive method that checks getters and setters for the fields of a class and its superclass.

public class TestClass {
	public static void main(String[] args) {
		
		ProductDataClass_MissingGetterMethod pdm = new ProductDataClass_MissingGetterMethod();
		ProductDataClass_WrongGetterMethodReturnType classWrongReturnType = new ProductDataClass_WrongGetterMethodReturnType();
		
		GetterAndSetterMethodChecker.testGetters(pdm.getClass());
		GetterAndSetterMethodChecker.testGetters(classWrongReturnType.getClass());
		GetterAndSetterMethodChecker.testSetters(pdm.getClass());
		
	}
}
