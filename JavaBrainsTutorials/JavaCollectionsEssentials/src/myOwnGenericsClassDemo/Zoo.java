package myOwnGenericsClassDemo;

public class Zoo {
	
	public static void main(String[] args) {
		
		//By Using generics you make your code i.e a cage of monkey will not store lion in it. and also if a new animal is created you can create a cage for them without creating a new class. 
		//If something is wrong on the type of data stored on your cage you can see it before it goes to production. 
		
		Cage<Monkey> mokeyCage = new Cage<>();
		mokeyCage.setAnimal1(new Monkey());
		mokeyCage.setAnimal2(new Monkey());
		
		//If you want to create using constructor do it like this. 
		Cage<Monkey> monkeyCage2 = new Cage<>(new Monkey(), new Monkey());
		
		
		Cage<Lion> lionCage = new Cage<>();
		lionCage.setAnimal1(new Lion());
		lionCage.setAnimal2(new Lion());
		
		//Note: since you class Cage accepts a parameter that only extends Animal then you Cage class only used to a type that is Animal only. So you can not create something like Cage<String> 
		// because of we use E extends animal that means our compiler at least now E is a type of Animal so you can use method that are a part of Animal using E. 
		
		
		//Since Cage class has static method you can call it like: 
		Monkey m1 = new Monkey();
		Monkey m2 = new Monkey();
		Cage.isCompatible(m1, m2);
	}

}
