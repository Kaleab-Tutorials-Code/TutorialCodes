package c_fields_modification_and_arrays_creation_implement_file_parser.exercise4_readarraywithreflection;

import java.lang.reflect.Array;

//Note: 

//object.getClass().isArray(): to test if an object is an array.
//Array.get(object, i): to get element value an index i of an array.
//Array.getLength(object): to get array Length;


public class Main {

	public static void main(String[] args) {
		int[] oneDimesionalArray = {1,2};
		int[][] twoDimensionalArray = {{1,2}, {3,4}, {5,6}};
		
		inspectArrayObject(oneDimesionalArray);
		inspectArrayValues(oneDimesionalArray);
		
		inspectArrayObject(twoDimensionalArray);
		inspectArrayValues(twoDimensionalArray);
	}
	
	public static void inspectArrayValues(Object arrayObject) {
		int arrayLength = Array.getLength(arrayObject);
		
		System.out.print("[");
		for(int i = 0; i< arrayLength; i++) {
			Object element = Array.get(arrayObject, i);
			if(element.getClass().isArray()) {
				inspectArrayValues(element);
			}
			else {
				System.out.print(element);
			}
			
			if(i < arrayLength - 1) System.out.print(",");
		}
		
		System.out.print("]");
		
	}
	
	public static void inspectArrayObject(Object arrayObject) {
		Class<?> clazz = arrayObject.getClass();
		System.out.println(String.format("Is array : %s", clazz.isArray()));
		
		Class<?> arrayComponentType = clazz.getComponentType();
		
		System.out.println(String.format("This is an array type : %s", arrayComponentType.getTypeName()));
	}

}
