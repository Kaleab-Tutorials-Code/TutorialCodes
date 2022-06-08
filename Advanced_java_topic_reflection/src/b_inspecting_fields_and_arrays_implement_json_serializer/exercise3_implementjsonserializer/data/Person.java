package b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.data;

public class Person {
	
	private final String name;
	private final boolean employed;
	private final int age;
	private final float salary;
	
	private final Address address;

	public Person(String name, boolean employed, int age, float salary, Address address) {
		super();
		this.name = name;
		this.employed = employed;
		this.age = age;
		this.salary = salary;
		this.address = address;
	}

}
