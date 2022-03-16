package cracking_code_inteview_qa;

import java.sql.Date;

public class ArraysAndStrings {

	public static void main(String[] args) {
		//Test isUniqueCharacterString method
		String s1 = "Hello";
		String s2 = "abcd";
		String s3 = "aabb";
		

	}
	
	//Lessons:
	//for ASCII String consider using an array with 128 length to store information about characters if you are not allowed to use any data structure other than Array itself.
	//If you are allowed to use array, consider using utility methods provided by Array. i.e Arrays.sort.
	
	//#QUESTION 1: IsUnique
	
	//Implement an algorithm to determine if a string has all unique characters. what if you can not use additional data structures.
	public boolean isUniqueCharacterString(String input) {
		
		if(input == null || input.length() == 0) return false;
		
		for(int i = 0; i < input.length() - 1 ; i++) {
			for(int j = i + 1; j < input.length(); j++) {
				if(input.charAt(i) == input.charAt(j)) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean solutionIsUniqueCharacterString(String input) {
		//Ask if a string is ASCII String or UNICODE string 
		//ASCII: it is just a character encoding standard that used to represent 128 character using number from 0 - 127. example A represented by 65.
		//Unicode: it provides a unique way to define every character in every spoken language of the world by assigning it a unique number. that include emojis.
		// Unicode charcter can be defined with d/t encoding like UTF-8, UTF-16 .....
		
		//So you ask the interviewer if a string is ASCII or Unicode ? Lets say it is ASCII String => all character in a string are represented with number from 0 - 127. 
		//Note that: on Unicode those characters represented by ASCII represented with same number.
		
		if(input == null || input.length() == 0) return false;
		boolean[] char_set = new boolean[128];
		
		for(int i = 0; i < input.length(); i++) {
			char val = input.charAt(i);
			if(char_set[val] == true) {
				return false;
			}
			
			char_set[val] = true;
		}
		
		return true;
	}
	
	//#QUESTION 2: 
	
	//1.2 Check Permutation: Given two strings, write a method to decide if one is a permutation of the other. 
	public boolean solutionIsPermtuationOneAnother(String input1, String input2) {
		//Step 1: Ask if the solution is caseSensitive, consideres white space ...
		if(input1.length() != input2.length()) return false;
		return sort(input1).equals(sort(input2));
	}
	
	private String sort(String input) {
		char[] c = input.toCharArray();
		java.util.Arrays.sort(c);
		return new String(c);
	}
	
	public boolean solution2_isPermtuationOneAnother(String input1, String input2) {
		//This one has a better performance. But it can only works if a string is ASCII format.
		
		if(input1.length() != input2.length()) return false;
		int[] permChecker = new int[128];
		
		for(int i =0; i < input1.length(); i++) {
			permChecker[input1.charAt(i)]++;
		}
		
		for(int i =0; i < input2.length(); i++) {
			permChecker[input2.charAt(i)]--;
			if(permChecker[input2.charAt(i)] < 0) return false;
		}
		
		
		return true;
	}
	
	//#QUESTION 3: URLify: ==> My Solution is best so i don't need to refer the book solution.
	
	//implement a method to replace all spaces in string with '%20'. You may assume that the string has sufficient space at the end to hold the additional characters, 
	//and that you are given the "true" length of the string. (Note: if implementing in java, please use a character array so that you can perform this operation in place.)
	//example input: "Mr John Smith    ", 13  output: "Mr%20John%20Smith" 
	public String urlify(String input, int length) {
		
		for(int i = 0; i < length; i++) {
			if(input.charAt(i) == ' ') {
				input = input.substring(0, i) + "%20" + input.substring(i + 1, length);
				i = i + 2;
				length = length + 2;
			}
		}
		return input.substring(0, length);
	}
	
	public String urlify_with_stringBuilder(String input, int length) {
		long startTime= System.currentTimeMillis();
		StringBuilder output = new StringBuilder();
		
		for(int i = 0; i < length; i++) {
			if(input.charAt(i) == ' ') {
				output.append("%20");
			}
			else {
				output.append(input.charAt(i));
			}
		}
		
		System.out.println("Method Kaleab" + (System.currentTimeMillis() - startTime));
		return output.toString();
	}
	
//This memar solution is slower than mine because of substring method used frequently.
	public String urlify_with_stringBuilder_Memar(String input, int length) {
		long startTime= System.currentTimeMillis();
		StringBuilder output = new StringBuilder();
		int startIndex = 0;
		
		for(int i = 0; i < length; i++) {
			
			if(input.charAt(i) == ' ') {
				
				output.append(input.substring(startIndex, i)).append("%20");
				startIndex = i + 1;
			}
		}
		
		output.append(input.substring(startIndex, length));
		System.out.println("Method Memar" + (System.currentTimeMillis() - startTime));
		return output.toString();
	}
	
	//#QUESTION 4: Given a string, write a function to check if it is a permutation of a palindrome. a palindrome is a word or phrase that is the same forwards and backwards. 
	
	//a permutation is rearrangment of letters. The palindrome does not need to be limited to just dictionary words. you can ignore casing and non-letter characters. 
	
	//example: input: Tact Coa Output: True (permutations: "taco cat", "atco cta" etc).
	// if you see our input Tact Coa can be a permutation of palidrome word "taco cat". so output will be true. 
	
	public boolean isPermutationOfAPalindrome(String input) {
		
		//a palindrome with even length = should even count for each character.
		//a palidrome with odd length = should have even count for each and 1 additional character. 
		
		//for even count
		String lowerCaseInput = input.toLowerCase();
		int lenWithoutSpace = 0;
		int trueCount = 0;
		int falseCount = 0;
		int[] char_count = new int[128];
		
		for(int i = 0; i< lowerCaseInput.length(); i++) {
			char chatAtIndex = lowerCaseInput.charAt(i);
			if(chatAtIndex != ' '){
				lenWithoutSpace++;
				char_count[chatAtIndex]++;
				if(char_count[chatAtIndex] % 2 == 0) {
					trueCount++;
				} 
				else {
					falseCount++;
				}
			}	
		}
		
		return lenWithoutSpace % 2 == 0 ? trueCount == falseCount : trueCount+1 == falseCount;
	}
	
	//Comment: my logic is just working fine. I prefer my solution more than the book solution.
	
	
	//QUESTION 5: One Edit Away: 
	// There are three types of edits that can be performed on strings: insert a character, remove a character, or replace a character. Given two strings, write a function to check if they are 
	//one edit (or zero edits ) away. Implement an algorithm for that. 
	
	public boolean isOneOrZeroEditAway(String input1, String input2) {	
		int input1Length = input1.length();
		int input2Length = input2.length();
		
		if(Math.abs(input1Length - input1Length) > 1) return false;
		
		if(input1Length == input2Length) return oneEditReplaceAway(input1, input2);
		if(input1Length < input2Length) return oneEditInsertAway(input1, input2);
		return oneEditInsertAway(input2, input1);
	}
	
	private boolean oneEditReplaceAway(String input1, String input2) {
		boolean foundDifference = false;
		int index = 0;
		while(index < input1.length() ) {	
			if(input1.charAt(index) != input2.charAt(index)) {
				if(foundDifference) return false;
				foundDifference = true;
			}
			index++;
		}
		
		return true;
	}
	
	private boolean oneEditInsertAway(String input1, String input2) {
		int index1 = 0;
		int index2 = 0;
		while(index1 < input1.length()) {
			
			if(input1.charAt(index1) != input2.charAt(index2)) {
				if(index2 != index1) {
					return false;
				}
				index2++;
			}
			else {
				index1++;
				index2++;
			}
			
		}
		return true;
	}
	
	//QUESTION 6: 
	//Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. if the "Compressed" string would not
	//become smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase letters(a-z).
	
	public String compressedString(String input) {
		
		StringBuilder compressedString = new StringBuilder("");
		int length = input.length();
		int compressedCharLength = 0;
		int char_counter = 0;
		
		for(int i = 0; i < length ; i++) {
			if(i == 0) {
				compressedString.append(input.charAt(i));
				char_counter++;
				compressedCharLength++;
			}
			else {
				if(input.charAt(i) == input.charAt(i - 1)) char_counter++;
				else 
				{
					compressedString.append(char_counter).append(input.charAt(i));
					compressedCharLength = compressedCharLength + 2;
					char_counter = 1;
				}
				}
			
		      if(compressedCharLength >= length) return input;
			}
		
		compressedString.append(char_counter);
		
		return compressedString.toString();
	}
	
	//next step see the book solution for this. 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
