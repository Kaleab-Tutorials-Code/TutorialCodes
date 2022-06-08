package f_java_modifier_discovery_and_analysis_improve_json_serializer;

public class Address {
	private static final int ZIP_CODE_MAX_DIGITS = 5;
	private final transient String zipCode;
	private final String street;
	private final short apartment;
	
	public Address(String street, short apartment, String zipCode) {
		this.street = street;
		this.apartment = apartment;
		this.zipCode = zipCode;
	}
	
}
