package cracking_code_inteview_qa;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LinkedLists {

	public static void main(String[] args) {
		LinkedList<Integer> ll = new LinkedList<>();
		ll.add(10);
		ll.add(8);
		ll.add(8);
		ll.add(10);
		ll.add(10);
		ll.add(10);
		ll.add(10);
		ll.add(9);
		ll.add(9);
		ll.add(6);
		ll.add(7);
		ll.add(9);
		
		removeDupsFromUnsortedLinkedList(ll);

		System.out.print(ll);

	}

	public static void removeDupsFromUnsortedLinkedList(LinkedList<Integer> integerLinkedList) {
		// my task is remove duplicates from this linked list. and return a linked list
		// without duplicated values.
		
		Map<Integer, Integer> map = new HashMap<>(); 
		for(int i = 0; i < integerLinkedList.size(); i++) {
			int currentElement = integerLinkedList.get(i);
			map.put(currentElement, map.getOrDefault(currentElement, 0) + 1);
			if(map.get(currentElement) > 1) {
				integerLinkedList.remove(i);
				i = i-1;
			}
		}
		
		System.out.println(map);
	}

}
