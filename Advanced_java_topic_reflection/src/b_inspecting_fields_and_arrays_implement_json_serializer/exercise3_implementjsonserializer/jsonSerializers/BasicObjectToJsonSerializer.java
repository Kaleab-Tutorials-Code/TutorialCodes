package b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.jsonSerializers;

import java.lang.reflect.Field;

public class BasicObjectToJsonSerializer {
	
	public static String objectToJson(Object instance) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = instance.getClass().getDeclaredFields();
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("{");
		
		int fieldsLength = fields.length;
		
		for(int i = 0; i < fieldsLength; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			
			if(field.isSynthetic()) {
				continue;
			}
			
			stringBuilder.append(formatStringValue(field.getName()));
			stringBuilder.append(":");
			
			if(field.getType().isPrimitive()) {
				stringBuilder.append(formatPrimitiveValue(field , instance));
			}
			else if(field.getType().equals(String.class)) {
				stringBuilder.append(formatStringValue(field.get(instance).toString()));
			}
			
			if(i != fieldsLength - 1) {
				stringBuilder.append(",");
			}
		}
		
		stringBuilder.append("}");
		
		return stringBuilder.toString();
	}
	
	
	//This method just add double quote on string values
	private static String formatStringValue(String value) {
		return String.format("\"%s\"", value);
	}
	
	//This method just get the value of a primitive type field and if it is float or double it will keep the decimal places to two.
	private static String formatPrimitiveValue(Field field, Object parentInstance) throws IllegalArgumentException, IllegalAccessException {
		if(field.getType().equals(boolean.class)
				|| field.getType().equals(int.class)
				|| field.getType().equals(long.class)
				|| field.getType().equals(short.class)) {
			return field.get(parentInstance).toString();
		}
		else if(field.getType().equals(double.class) || field.getType().equals(float.class)) {
			return String.format("%.02f", field.get(parentInstance));
		}
		
		throw new RuntimeException(String.format("Type : %s is unsupported", field.getType().getName()));
	}
}
