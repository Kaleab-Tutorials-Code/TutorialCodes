package f_java_modifier_discovery_and_analysis_improve_json_serializer;

public class TestClass {
  public static void main(String[] args) throws IllegalAccessException {
	Address address = new Address("Main Street", (short) 1, "12345");
	System.out.println(JsonWriter.objectToJson(address));
}
}
