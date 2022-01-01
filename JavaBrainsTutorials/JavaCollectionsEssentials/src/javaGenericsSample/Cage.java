package javaGenericsSample;

public class Cage<E extends Animal> {

	private E animal1;
	private E animal2;
	
	
	public Cage(E animal1, E animal2) {
		super();
		this.animal1 = animal1;
		this.animal2 = animal2;
	}
	
	
	
	public Cage() {
		super();
		// TODO Auto-generated constructor stub
	}


	public E getAnimal1() {
		return animal1;
	}
	public void setAnimal1(E animal1) {
		this.animal1 = animal1;
	}
	public E getAnimal2() {
		return animal2;
	}
	public void setAnimal2(E animal2) {
		this.animal2 = animal2;
	} 
	
	
	//This is how you implement static method inside a class with Generics.
	public static <E extends Animal >boolean isCompatible(E animal1, E animal2) {
		return animal1.getType().equals(animal2.getType());
	}
	
	
}
