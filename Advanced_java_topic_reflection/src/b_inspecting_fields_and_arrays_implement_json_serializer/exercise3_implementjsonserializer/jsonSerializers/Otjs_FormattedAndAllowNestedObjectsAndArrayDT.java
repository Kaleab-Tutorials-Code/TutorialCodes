package b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.jsonSerializers;


import java.lang.reflect.Array;
import java.lang.reflect.Field;

public class Otjs_FormattedAndAllowNestedObjectsAndArrayDT {
	
	
	//Object to Json implementation that supports primitive, String and Array values
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
				stringBuilder.append(formatPrimitiveValue(field.get(instance) , field.getType()));
			}
			else if(field.getType().equals(String.class)) {
				stringBuilder.append(formatStringValue(field.get(instance).toString()));
			}
			else if(field.getType().isArray()) {
				stringBuilder.append(arrayToJson(field.get(instance), indentSize + 1));
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
	
	private static String formatPrimitiveValue(Object instance, Class<?> type) throws IllegalArgumentException, IllegalAccessException {
		if(type.equals(boolean.class)
				|| type.equals(int.class)
				|| type.equals(long.class)
				|| type.equals(short.class)) {
			return instance.toString();
		}
		else if(type.equals(double.class) || type.equals(float.class)) {
			return String.format("%.02f", instance);
		}
		
		throw new RuntimeException(String.format("Type : %s is unsupported", type.getTypeName()));
	}
	
	//This method is used to parse Array into Json. If array contains object in it, it call objectToJson method to parse it.
	public static String arrayToJson(Object arrayInstance, int indentSize) throws IllegalArgumentException, IllegalAccessException {
		int arrayLength = Array.getLength(arrayInstance);
		Class<?> componentType = arrayInstance.getClass().getComponentType();
		
		StringBuilder aa = new StringBuilder();
		aa.append("[");
		aa.append("\n");
		
		for(int i = 0; i< arrayLength; i++) {
			Object element = Array.get(arrayInstance, i);
			aa.append(indent(indentSize + 1));
			if(componentType.isPrimitive()) {
				aa.append(formatPrimitiveValue(element, componentType));
			}
			else if(componentType.equals(String.class)) {
				aa.append(formatStringValue(element.toString()));
			}
			else {
				//This will be executed if array element is class itself.
				aa.append(objectToJson(element, indentSize + 1));
			}
			
			if(i < arrayLength - 1) {
				aa.append(",");
			};
			aa.append("\n");
		}
		
		aa.append(indent(indentSize));
		aa.append("]");
		
		return aa.toString();
		
	}
}
