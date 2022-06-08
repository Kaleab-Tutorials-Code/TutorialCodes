package a_object_creation_and_construction.exercise1_ideinformationexample;

public class TestClass {

	public static void main(String[] args) {
		Employee e = new Employee(1, "kaleab", "6421232", Sex.MALE);
		
		System.out.print(Exercise.createPopupTypeInfoFromClass(e.getClass()));

	}

}
