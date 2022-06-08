package b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.jsonSerializers;


import java.lang.reflect.Field;

public class Otjs_FormattedAndAllowNestedObjects {
	
	
	public static String objectToJson(Object instance, int indentSize) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = instance.getClass().getDeclaredFields();
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(indent(indentSize));
		stringBuilder.append("{");
		stringBuilder.append("\n");
		int fieldsLength = fields.length;
		
		for(int i = 0; i < fieldsLength; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			
			if(field.isSynthetic()) {
				continue;
			}
			
			stringBuilder.append(indent(indentSize + 1));
			stringBuilder.append(formatStringValue(field.getName()));
			stringBuilder.append(":");
			
			if(field.getType().isPrimitive()) {
				stringBuilder.append(formatPrimitiveValue(field , instance));
			}
			else if(field.getType().equals(String.class)) {
				stringBuilder.append(formatStringValue(field.get(instance).toString()));
			}
			else {
				//if a field type is an object then we further serialize it by calling the same method recursively
				stringBuilder.append(objectToJson(field.get(instance), indentSize + 1));
			}
			
			if(i != fieldsLength - 1) {
				stringBuilder.append(",");
			}
			stringBuilder.append("\n");
		}
		
		stringBuilder.append(indent(indentSize));
		stringBuilder.append("}");
		
		return stringBuilder.toString();
	}
	
	private static String indent(int indentSize) {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < indentSize; i++) {
			stringBuilder.append("\t");
		}
			return stringBuilder.toString();
	}
	
	
	private static String formatStringValue(String value) {
		return String.format("\"%s\"", value);
	}
	
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