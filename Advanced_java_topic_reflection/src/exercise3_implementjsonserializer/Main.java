package exercise3_implementjsonserializer;

import java.lang.reflect.Field;

import exercise3_implementjsonserializer.data.Address;
import exercise3_implementjsonserializer.data.Person;
import exercise3_implementjsonserializer.jsonSerializers.BasicObjectToJsonSerializer;
import exercise3_implementjsonserializer.jsonSerializers.ObjectToJsonSerializer_Formatted;
import exercise3_implementjsonserializer.jsonSerializers.ObjectToJsonSerializer_FormattedAndAllowNestedObjects;

public class Main {
	
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Address address = new Address("Main Street", (short)1);
		Person person = new Person("kaleab", true, 30, 100.55f, address);
		
		String json = BasicObjectToJsonSerializer.objectToJson(address);
		String jsonFormatted = ObjectToJsonSerializer_Formatted.objectToJson(address, 0);
		String jsonFormattedAndNested = ObjectToJsonSerializer_FormattedAndAllowNestedObjects.objectToJson(person, 0);
		
		System.out.println(json);
		
		System.out.println("Formatted Json");
		System.out.println("--------------");
		
		System.out.println(jsonFormatted);
		
		System.out.println("Formatted Nested Json");
		System.out.println("--------------");
		
		System.out.println(jsonFormattedAndNested);
		
	}
	
	
	

}
