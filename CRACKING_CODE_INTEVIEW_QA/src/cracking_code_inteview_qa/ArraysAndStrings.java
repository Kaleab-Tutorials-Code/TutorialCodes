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
	
	//Note that: string concatination takes O(n2) time. I think it is because of we need to copy each element every time when adding new character on string. 
	//So using string builder solves this issue.
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
	
	public String solutionCompressedString(String input) {
		
		StringBuilder compressedString = new StringBuilder();
		int char_counter = 0;
		int length = input.length();
		int compressedLength = 0;
		
		for(int i = 0; i < length; i++) {
			char_counter++;
			
			if(i + 1 >= length || input.charAt(i) != input.charAt(i + 1)) {
				compressedString.append(input.charAt(i)).append(char_counter);
				compressedLength = compressedLength + 2;
				char_counter = 0;
			}
			
			if(compressedLength >= length) return input;
		}
		
		return compressedString.toString();
	}
	
	//QUESTION 7: Rotate Matrix
	//Given an image representation by an N X N matrix, where each pixel in the image is represented by an integer, write a method to rotate the image by 90 degrees. can you do this in place ?
	
	public int[][] solutionRotateMatrix(int[][] inputMatrix) throws Exception {
	    int length = inputMatrix.length;
	    int columnLength = inputMatrix[0].length;
	    if(length != columnLength) throw new Exception("matrix is not equal in length");
	    
	    for(int layer = 0; layer < length / 2; layer++) {  //we need to iterate 2 times. 
	    	int first = layer;  // first is incremented by one 0 -> 1.
	    	int last = length - 1 - layer; // 3 -> 2
	    	
	    	for(int i = first; i < last; i++) {  //first you have to swap three times then 1 times.
	    		
	    		int offset = i - first;
	    		int top = inputMatrix[first][i];  //top should be 00 then 01 then 02 -> first Index always = first. and second Index 0 -> 1 -> 2 => i.
	    		//copy left values to top
	    		inputMatrix[first][i] = inputMatrix[last - offset][first]; //left values 30 -> 20 -> 10 , second index is first but first index should be decremented => last - offset.
	    		//copy bottom values to left
	    		inputMatrix[last - offset][first] = inputMatrix[last][last - offset];
	    		//copy right values to bottom
	    		inputMatrix[last][last - offset] = inputMatrix[i][last];
	    		//copy top values to right
	    		inputMatrix[i][last] = top;
	    		
	    	}
	    	
	    }
	
	    return inputMatrix;
	
	}
	
	//QUESTION 8: Zero Matrix
	// write an algorithm such that if an element in an M X N matrix is 0, its entire row and column are set to 0.
	//my solution is the same as book solution.

	public int[][] zeroMatrix(int[][] inputMatrix){
		int rowLength = inputMatrix.length;
		int columnLength = inputMatrix[0].length;
		
		boolean[] columnIndexesToBeZero = new boolean[columnLength];
		boolean[] rowIndexesToBeZero = new boolean[rowLength];
		
		for(int i = 0; i < rowLength; i++) {
			for(int j = 0; j < columnLength; j++) {
				if(inputMatrix[i][j] == 0) {
					columnIndexesToBeZero[j] = true;
					rowIndexesToBeZero[i] = true;
				}
			}
		}	
		
		for(int i = 0; i < columnLength; i++) {
			if(columnIndexesToBeZero[i]) makeColumnZero(inputMatrix, i, rowLength);
		}
		
		for(int i = 0; i < rowLength; i++) {
			if(rowIndexesToBeZero[i]) makeRowZero(inputMatrix, i, columnLength);
		}
		
		printArray(inputMatrix, rowLength, columnLength);
		return inputMatrix;
	}
	
	//Other Solution from the book: 
		//If you want to save some space you can do the following: 
		//-> check if first row and first column has any zero. if so at the end you can make entire row/ entire column zero based on the result.
		//-> then use first row and first column as columnIndexesToBeZero and rowIndexesToBeZero.
		//-> by doing this previously i took O(N) space but now it will be O(1) space complexity.
	public int[][] zeroMatrix_betterSpaceComplexity(int[][] inputMatrix){
		int rowLength = inputMatrix.length;
		int columnLength = inputMatrix[0].length;
		
		boolean firstRowHasZero = false;
		boolean firstColumnHasZero = false;
		
		for(int i = 0; i < columnLength; i++) {
			if(inputMatrix[0][i] == 0) {
				firstRowHasZero = true;
				break;
			}
		}
		
		for(int i = 0; i < rowLength; i++) {
			if(inputMatrix[i][0] == 0) {
				firstColumnHasZero = true;
				break;
			}
		}
		
		for(int i = 1; i < rowLength; i++) {
			for(int j = 1; j < columnLength; j++) {
				if(inputMatrix[i][j] == 0) {
					inputMatrix[i][0] = 0;
					inputMatrix[0][j] = 0;
				}
			}
		}	
		
		for(int i = 0; i < columnLength; i++) {
			if(inputMatrix[0][i] == 0) makeColumnZero(inputMatrix, i, rowLength);
		}
		
		for(int i = 0; i < rowLength; i++) {
			if(inputMatrix[i][0] == 0) makeRowZero(inputMatrix, i, columnLength);
		}
		
		if(firstRowHasZero) {
			makeRowZero(inputMatrix, 0, columnLength);
		}
		
		if(firstColumnHasZero) {
			makeColumnZero(inputMatrix, 0, rowLength);
		}
		
		printArray(inputMatrix, rowLength, columnLength);
		
		return inputMatrix;
	}
	
	private void makeColumnZero(int[][] inputMatrix, int coln, int rowSize) {
		for(int i = 0; i < rowSize; i++) {
			inputMatrix[i][coln] = 0;
		}
	}
	
	private void makeRowZero(int[][] inputMatrix, int row, int colnSize) {
		for(int i = 0; i < colnSize; i++) {
			inputMatrix[row][i] = 0;
		}
	}
	
	private void printArray(int[][] inputMatrix, int rowsize, int colnSize) {
		for(int i = 0; i< rowsize; i++) {
			for(int j = 0; j < colnSize; j++) {
				System.out.print(inputMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	//QUESTION 9: String Rotation: 
	//Assume you have a method isSubstring which checks if one word is a substring of another. Given two strings, s1 and s2, write a code to check if s2 is a rotation of s1 using only one call
	//to isSubstring (e.g., "waterbottle" is a rotation of "erbottlewat").
	
	public boolean isSubstring(String input1, String input2) {
		if(input1.length() != input2.length()) return false;
		
		int length = input1.length();
		char firstCharacter = input1.charAt(0);
		
			for(int i = 0; i < length; i++) {
				if(input2.charAt(i) == firstCharacter) {
					if(checkRotation(input1, input2, i)) return true;
				}
			}
		
		
		return false;
	}
	
	private boolean checkRotation(String input1, String input2, int input2CharacterMatchIndex) {
		int length = input1.length();
		for(int i = 0; i < length; i++) {
			if(input2CharacterMatchIndex >= length) input2CharacterMatchIndex = 0;
			if(input2.charAt(input2CharacterMatchIndex) != input1.charAt(i)) return false;
			input2CharacterMatchIndex++;
		}
		
		return true;
	}
	
	//I will check book solution for this one.
	
	
	
	
	
	
	
	
	
	
	


}
