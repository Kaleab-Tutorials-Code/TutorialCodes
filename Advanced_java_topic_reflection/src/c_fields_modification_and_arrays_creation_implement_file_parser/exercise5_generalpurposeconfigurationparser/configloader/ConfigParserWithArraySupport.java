package c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser.configloader;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

//This parser add array support in addition to primitive values from configuration.
//It knows the the type is array or not using class fieldName types that matches the configuration property name

//Important methods to know
//Array.newInstance(type, arrayLength): used to create an array with specified length;
//Array.set(arrayObject, index, value): used to add a value into array object at specified index

public class ConfigParserWithArraySupport {
	public static <T> T createConfigObject(Class<T> clazz, Path filePath) throws IllegalArgumentException, IllegalAccessException, IOException, InstantiationException, InvocationTargetException {
		Scanner scanner = new Scanner(filePath);
		
		//I am trying to get the no param constructor
		Constructor<?> constructor = null;
		for(Constructor<?> constr: clazz.getDeclaredConstructors()) {
			if(constr.getParameterCount() == 0 ) {
				constructor = constr;
				constructor.setAccessible(true);
				break;
			}
		}
		
		//Create instance using constructor
		T configInstance = (T) constructor.newInstance();
		
		//loop through the configuration and get propertyname and value
		while(scanner.hasNextLine()) {
			String configLine = scanner.nextLine();
			
			String[] nameValuePair = configLine.split("=");
			String propertyName = nameValuePair[0];
			String propertyValue = nameValuePair[1];
			
			Field field;
			try {
				field = clazz.getDeclaredField(propertyName);
			}
			catch(NoSuchFieldException e) {
				System.out.println(String.format("Property name: %s is unsupported", propertyName));
				continue;
			}
			
			field.setAccessible(true);
			
			//parse the value from configuration based on field type i.e if it is double we need store it as double in object
			Object parsedValue;
			
			if(field.getType().isArray()) {
				parsedValue = parseArray(field.getType().getComponentType(), propertyValue);
			}
			else {
				parsedValue = parseValue(field.getType(), propertyValue);
			}
			
			//set the value to your field. here we don't use constructor because we don't know which field value is for which param
			field.set(configInstance, parsedValue);
		}
		
		//finally return the parsed object from the configuration
		return configInstance;
		
	}
	
	public static Object parseArray(Class<?> arrayElementType, String value) {
		String[] elementValues = value.split(",");
		
		Object arrayObject = Array.newInstance(arrayElementType, elementValues.length);
		for(int i = 0; i < elementValues.length; i++) {
			Array.set(arrayObject, i, parseValue(arrayElementType, elementValues[i]));
		}
		
		return arrayObject;
	}
	
	//Note that: currently our parser only support this value types. we will also add other for future exercises
	public static Object parseValue(Class<?> type, String value) {
		if(type.equals(int.class)) return Integer.parseInt(value);
		else if(type.equals(short.class)) return Short.parseShort(value);
		else if(type.equals(long.class)) return Long.parseLong(value);
		else if(type.equals(double.class)) return Double.parseDouble(value);
		else if(type.equals(float.class)) return Float.parseFloat(value);
		else if(type.equals(String.class)) return value;
		
		throw new RuntimeException(String.format("Type : %s is unsupported", type.getTypeName()));
	}
}
