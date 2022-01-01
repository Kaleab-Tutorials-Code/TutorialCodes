package javaCollectionEssential;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Iterators {

	public static void main(String[] args) {
		
		//foreachremaining method is used to to anything you want for the remaining element of the list on which your iterator is not iterated through.
		//what is does is the same as its name saying.
		
		List<String> stringArray = Arrays.asList("a", "b", "c", "d");
		
		
		Iterator<String> iterator = stringArray.iterator();
		Iterator<String> iterator2 = stringArray.iterator();
		iterator2.forEachRemaining((a) -> System.out.println(a + "here"));
		int i = 0;
		
		while(iterator.hasNext()) {
			i++;
			System.out.println(iterator.next());
			iterator.forEachRemaining((a) -> System.out.println(a + "here"));
		}
		
		System.out.println(i);
		
		
		// TODO Auto-generated method stub

	}

}

