package b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer;

import java.lang.reflect.Field;

import b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.data.Actor;
import b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.data.Address;
import b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.data.Person;
import b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.jsonSerializers.BasicObjectToJsonSerializer;
import b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.jsonSerializers.ObjectToJsonSerializer_Formatted;
import b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.jsonSerializers.Otjs_FormattedAndAllowNestedObjects;
import b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.jsonSerializers.Otjs_FormattedAndAllowNestedObjectsAndArrayDT;

public class Main {
	
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Address address = new Address("Main Street", (short)1);
		Address address2 = new Address("Another Street", (short)2);
		
		Address[] addresses = {address, address2};
		
		Person person = new Person("kaleab", true, 30, 100.55f, address);
		String[] moviesList = {"The Lord of the Ring", "God Must be Crazy"};
		Actor actor = new Actor(person, moviesList, addresses);
		
		
		String json = BasicObjectToJsonSerializer.objectToJson(address);
		String jsonFormatted = ObjectToJsonSerializer_Formatted.objectToJson(address, 0);
		String jsonFormattedAndNested = Otjs_FormattedAndAllowNestedObjects.objectToJson(person, 0);
		String jsonFormattedAndNestedAndArray = Otjs_FormattedAndAllowNestedObjectsAndArrayDT.objectToJson(actor, 0);
		
		System.out.println(json);
		
		System.out.println("Formatted Json");
		System.out.println("--------------");
		
		System.out.println(jsonFormatted);
		
		System.out.println("Formatted Nested Json");
		System.out.println("-----------------------");
		
		System.out.println(jsonFormattedAndNested);
		
		System.out.println("Formatted Nested Json That have Array, Array also can have objects in it");
		System.out.println("-------------------------------------------------------------------------");
		
		System.out.println(jsonFormattedAndNestedAndArray);
		
	}
	
	
	

}
