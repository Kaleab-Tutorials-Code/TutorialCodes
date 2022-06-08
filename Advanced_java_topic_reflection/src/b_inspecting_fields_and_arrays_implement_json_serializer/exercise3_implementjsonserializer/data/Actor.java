package b_inspecting_fields_and_arrays_implement_json_serializer.exercise3_implementjsonserializer.data;

public class Actor {
	
	private Person person;
	private final String[] knownForMovies;
	private final Address[] addresses;
	
	public Actor(Person person, String[] knownForMovies, Address[] addresses) {
		super();
		this.person = person;
		this.knownForMovies = knownForMovies;
		this.addresses = addresses;
	}
	
	

}
