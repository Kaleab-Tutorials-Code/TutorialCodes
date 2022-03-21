package exercise2_getallimplementationofaclass;

import java.util.*;
public class Exercise {
	
	public static void main(String[] args) {
		Set<Class<?>> interfaces = findAllImplementedInterfaces(A.class);
		for(Class<?> cc: interfaces) {
			System.out.println(cc.getSimpleName());
		}
	}
        
    /**
     * Returns all the interfaces that the current input class implements.
     * Note: If the input is an interface itself, the method returns all the interfaces the 
     * input interface extends.
     */
    public static Set<Class<?>> findAllImplementedInterfaces(Class<?> input) {
        Set<Class<?>> allImplementedInterfaces = new HashSet<>();
        
        Class<?>[] inputInterfaces = input.getInterfaces();
        for (Class<?> currentInterface : inputInterfaces) {
        	allImplementedInterfaces.add(currentInterface);
        	allImplementedInterfaces.addAll(findAllImplementedInterfaces(currentInterface));
        }

        return allImplementedInterfaces;
    }
}


class A implements B {}

interface B extends C, D {}

interface C extends D, F {}

interface D {}

interface F {}