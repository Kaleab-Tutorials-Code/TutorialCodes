package d_method_discovery_and_invocation.exercise6_implement_get_method_checker;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetterAndSetterMethodCheckerCheckAllMethodIncludingInherited {

	public static void testGetters(Class<?> dataClass) {
		List<Field> fields = getAllFields(dataClass);

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

	
	//For implenting testSetter we used Method.getMethod method instead of Method.getMethods and create map and get method by methodname as key.
	//Using same functionality we can shorten testGetter method.
	
	public static void testSetters(Class<?> dataClass) {
		List<Field> fields = getAllFields(dataClass);

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
	
	//as you see this method will fetch all method recursively. 
	//in one recursion 
	// ++ get all declaredFields
	// ++ then get all fields for super class by calling this method (this will do the same whole steps.)
	// ++ add both on the list and return.
	// ++ so at the end of the day i got all fields from a class and all its super classes
	
	//I can create the same method like getting allConstructors like getAllFields method
	private static List<Field> getAllFields(Class<?> clazz){
		if(clazz == null || clazz.equals(Object.class)) {
			return Collections.emptyList();
		}
		
		Field [] currentClassFields = clazz.getDeclaredFields();
		List<Field> inheritedFields = getAllFields(clazz.getSuperclass()); //Recursive call
		
		List<Field> allFields = new ArrayList<>();
		allFields.addAll(Arrays.asList(currentClassFields));
		allFields.addAll(inheritedFields);
		
		return allFields;
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
