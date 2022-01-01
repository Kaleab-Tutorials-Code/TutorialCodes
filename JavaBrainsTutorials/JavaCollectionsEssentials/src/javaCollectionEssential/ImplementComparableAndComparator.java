package javaCollectionEssential;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


//Note

//if you want to sort a list of object then Objects should implement Comparable Interface so that they can be compared. 
//Arrays.sort work for regular Arrays where as Collections.sort works for the new forms of Collections
//You can pass your own Comparator to this methods. If you don't provide a comparator then they can use compareTo method of an object

public class ImplementComparableAndComparator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Person p = new Person(1, "bbebe");
		Person p2 = new Person(2, "aekele");
		
		Person[] personArray = new Person[] {p2, p};
		Person[] personArray2 = new Person[] {p2, p};
		List<Person> personList = Arrays.asList(p2, p);
		List<Person> personList2 = Arrays.asList(p2, p);
		
		Arrays.sort(personArray);
		Arrays.sort(personArray2, new PersonComparator());
		
		Collections.sort(personList);
		
		
		System.out.println("Printed from the Array Compared using default object compareTo implementation");
		System.out.println("======================");
		
		for(int i = 0; i < personArray.length; i++) {
			System.out.println(personArray[i]);
		}
		
		
		System.out.println("Printed from the Array2  Compared using External Comparator");
		System.out.println("======================");
		
		for(int i = 0; i < personArray2.length; i++) {
			System.out.println(personArray2[i]);
		}
		
		System.out.println("Printed from the Array List Compared using default object compareTo implementation");
		System.out.println("===========================");
		
		personList.forEach((e) -> System.out.println(e));
		
		
		
		System.out.println("Printed from the Array List Compared using default object compareTo implementation and I use List method itself instead of using Collections Utility class");
		System.out.println("===========================");
		
		personList2.sort(new PersonComparator());
		
		personList2.forEach((e) -> System.out.println(e));
		

	}

}



class PersonComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
}



class Person implements Comparable<Person> {
	private int id; 
	private String name;
	
	
	
	public Person(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int compareTo(Person o) {
		return this.id - o.getId();
	} 
	
}
