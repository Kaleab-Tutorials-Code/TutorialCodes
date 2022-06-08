package d_method_discovery_and_invocation.exercise6_implement_get_method_checker;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GetterAndSetterMethodChecker {

	public static void testGetters(Class<?> dataClass) {
		Field[] fields = dataClass.getDeclaredFields();

		Map<String, Method> methodNameToMethod = mapMethodNameToMethod(dataClass);

		for (Field field : fields) {

			String getterName = "get" + capitalizeFirstLetter(field.getName());

			if (!methodNameToMethod.containsKey(getterName)) {
				throw new IllegalStateException(String.format("Field: %s doesn't have a getter method", getterName));
			}

			Method getter = methodNameToMethod.get(getterName);

			if (!getter.getReturnType().equals(field.getType())) {
				throw new IllegalStateException(
						String.format("Getter method : %s() has return type %s but expected as %s", getter.getName(),
								getter.getReturnType().getTypeName(), field.getType().getTypeName()));
			}
		}
	}

	
	//For implementing testSetter we used Method.getMethod method instead of Method.getMethods and create map and get method by method name as key.
	//Using same functionality we can shorten testGetter method.
	
	public static void testSetters(Class<?> dataClass) {
		Field[] fields = dataClass.getDeclaredFields();

		Method setterMethod = null;

		for (Field field : fields) {
			String setter = "set" + capitalizeFirstLetter(field.getName());

			try {
				//a setter method always have a parameter with type of the field and name set + fieldname.
				//so the following line try to get a method with this conditions.
				setterMethod = dataClass.getMethod(setter, field.getType());
			} catch (NoSuchMethodException e) {
				throw new IllegalStateException(String.format("Setter method : %s not found", setter));
			}

			if (setterMethod.getReturnType().equals(void.class)) {
				throw new IllegalStateException(String.format("Setter: %s has to return void", setter));
			}

		}
	}

	private static Map<String, Method> mapMethodNameToMethod(Class<?> dataClass) {
		// getter methods are always public so getMethods is used instead of
		// getDeclaredMethods()

		Map<String, Method> nameToMethod = new HashMap<>();
		Method[] allMethods = dataClass.getMethods();

		for (Method method : allMethods) {
			nameToMethod.put(method.getName(), method);
		}

		return nameToMethod;

	}

	private static String capitalizeFirstLetter(String fieldName) {
		return fieldName.substring(0, 1).toUpperCase().concat(fieldName.substring(1));
	}

}
