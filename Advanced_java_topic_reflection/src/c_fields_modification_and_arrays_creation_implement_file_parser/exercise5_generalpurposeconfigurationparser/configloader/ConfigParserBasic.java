package c_fields_modification_and_arrays_creation_implement_file_parser.exercise5_generalpurposeconfigurationparser.configloader;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Scanner;

//Here we are implementing a configuration parser that can convert config data into Java Object.
//setting value is done using fields instead of using constructor because we don't know which constructor parameter is for which field.
//Trying to update final of an object in reflection is not a good idea. some may erase unexpected behaviour. so avoid that.
public class ConfigParserBasic {
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
			Object parsedValue = parseValue(field.getType(), propertyValue);
			
			//set the value to your field. here we don't use constructor because we don't know which field value is for which param
			//set the field value of an instance to a specified value
			field.set(configInstance, parsedValue);
		}
		
		//finally return the parsed object from the configuration
		return configInstance;
		
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
