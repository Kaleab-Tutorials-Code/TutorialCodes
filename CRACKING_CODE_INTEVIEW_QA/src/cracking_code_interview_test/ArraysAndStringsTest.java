package cracking_code_interview_test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cracking_code_inteview_qa.ArraysAndStrings;

public class ArraysAndStringsTest {
	
	ArraysAndStrings aas;
	
	@BeforeEach
	public void setup() {
		aas = new ArraysAndStrings();
	}
	
	@Test
	public void Question1_isUniqueTest() {
		String s1 = "Hello";
		String s2 = "abcd";
		String s3 = "aabb";
		String s4 = "";
		String s5 = null;
		String s6 = "a";
		
		Assertions.assertAll("Conditions should be fulfilled",
				() -> assertEquals(false, aas.isUniqueCharacterString(s1)),
				() -> assertEquals(true, aas.isUniqueCharacterString(s2)),
				() -> assertEquals(false, aas.isUniqueCharacterString(s3)),
				() -> assertEquals(false, aas.isUniqueCharacterString(s4)),
				() -> assertEquals(false, aas.isUniqueCharacterString(s5)),
				() -> assertEquals(true, aas.isUniqueCharacterString(s6)),
				
				() -> assertEquals(false, aas.solutionIsUniqueCharacterString(s1)),
				() -> assertEquals(true, aas.solutionIsUniqueCharacterString(s2)),
				() -> assertEquals(false, aas.solutionIsUniqueCharacterString(s3)),
				() -> assertEquals(false, aas.solutionIsUniqueCharacterString(s4)),
				() -> assertEquals(false, aas.solutionIsUniqueCharacterString(s5)),
				() -> assertEquals(true, aas.solutionIsUniqueCharacterString(s6))
		);
		
	}
	
	@Test
	public void Question2_isPermtuationOneAnotherTest() {
		String s1 = "hello";
		String s2 = "olleh";
		String s3 = "elloh";
		String s4 = "hella";
		String s5 = "hel";
		String s6 = "lo";
		
		Assertions.assertAll("Conditions should be fulfilled",
				() -> assertEquals(true, aas.solutionIsPermtuationOneAnother(s1, s2)),
				() -> assertEquals(true, aas.solutionIsPermtuationOneAnother(s1,s3)),
				() -> assertEquals(false, aas.solutionIsPermtuationOneAnother(s1, s4)),
				() -> assertEquals(false, aas.solutionIsPermtuationOneAnother(s1,s5)),
				() -> assertEquals(false, aas.solutionIsPermtuationOneAnother(s1,s6)),
				
				() -> assertEquals(true, aas.solution2_isPermtuationOneAnother(s1, s2)),
				() -> assertEquals(true, aas.solution2_isPermtuationOneAnother(s1,s3)),
				() -> assertEquals(false, aas.solution2_isPermtuationOneAnother(s1, s4)),
				() -> assertEquals(false, aas.solution2_isPermtuationOneAnother(s1,s5)),
				() -> assertEquals(false, aas.solution2_isPermtuationOneAnother(s1,s6))
		);
		
	}
	
	@Test
	public void Question3_urlifyTest() {
		
		String s1 = "Mr John Smith ";
		
		assertEquals("Mr%20John%20Smith", aas.urlify(s1, 13));
		assertEquals("Mr%20John%20Smith", aas.urlify_with_stringBuilder(s1, 13));
		
		
	}
	
	@Test
	public void Question4_isPermutationOfAPalindrome() {
		
		assertEquals(true, aas.isPermutationOfAPalindrome("Tact Coa"));
		assertEquals(false, aas.isPermutationOfAPalindrome("abc"));
		assertEquals(true, aas.isPermutationOfAPalindrome("abcc ab"));
		assertEquals(false, aas.isPermutationOfAPalindrome("abcdec"));
		
	}
	
	@Test 
	public void Question5_isOneOrZeroEditAway() {
		assertEquals(true, aas.isOneOrZeroEditAway("pale", "pal"));
		assertEquals(true, aas.isOneOrZeroEditAway("pales", "pale"));
		assertEquals(true, aas.isOneOrZeroEditAway("ple", "pale"));
		assertEquals(true, aas.isOneOrZeroEditAway("pale", "bale"));
		assertEquals(false, aas.isOneOrZeroEditAway("pale", "bake"));
		assertEquals(false, aas.isOneOrZeroEditAway("pale", "pela"));
	}
	
	@Test 
	public void Question6_compressedString() {
		assertEquals("a2b2c3d4", aas.compressedString("aabbcccdddd"));
		assertEquals("abcd", aas.compressedString("abcd"));
		assertEquals("aabbcd", aas.compressedString("aabbcd"));
		assertEquals("aaaabcd", aas.compressedString("aaaabcd"));
		assertEquals("a4b4c1d1", aas.compressedString("aaaabbbbcd"));
		
		assertEquals("a2b2c3d4", aas.solutionCompressedString("aabbcccdddd"));
		assertEquals("abcd", aas.solutionCompressedString("abcd"));
		assertEquals("aabbcd", aas.solutionCompressedString("aabbcd"));
		assertEquals("aaaabcd", aas.solutionCompressedString("aaaabcd"));
		assertEquals("a4b4c1d1", aas.solutionCompressedString("aaaabbbbcd"));
		
		
	}
	
	@Test
	public void Question7_rotateMatrix() throws Exception {
		
		int[][] inputMatrix = {
				{ 1, 2 , 3, 4}, 
				{ 5, 6 , 7, 8},
				{ 9, 10 , 11, 12},
				{ 13, 14 , 15, 16}
				};
		int[][] expectedInputMatrix = {
				{ 13, 9 , 5, 1}, 
				{ 14, 10 , 6, 2},
				{ 15, 11 , 7, 3},
				{ 16, 12 , 8, 4}
				};
		
		int[][] inputMatrix2 = {
				{1,2},
				{3,4}
		};
		
		int[][] expectedInputMatrix2 = {
				{3,1},
				{4,2}
		};
		
		assertArrayEquals(expectedInputMatrix, aas.solutionRotateMatrix(inputMatrix));
		assertArrayEquals(expectedInputMatrix2, aas.solutionRotateMatrix(inputMatrix2));
		
		
	}
	
	@Test
	public void Question8_zeroMatrix() {
		int[][] inputMatrix = {
				{ 1, 2 , 3, 4}, 
				{ 5, 0 , 7, 8},
				{ 9, 10 , 11, 12},
				{ 13, 14 , 0, 16}
				};
		//I create inputMatrix1 because zeroMatrix will update the value of inputMatrix and so zeroMatrix_betterSpaceComplexity will be called with the updated inputMatrix => test failed.
		int[][] inputMatrix1 = {
				{ 1, 2 , 3, 4}, 
				{ 5, 0 , 7, 8},
				{ 9, 10 , 11, 12},
				{ 13, 14 , 0, 16}
				};
		int[][] expectedInputMatrix = {
				{ 1, 0 , 0, 4}, 
				{ 0, 0 , 0, 0},
				{ 9, 0 , 0, 12},
				{ 0, 0 , 0, 0}
				};
		
		int[][] inputMatrix2 = {
				{1,0},
				{3,4}
		};
		int[][] inputMatrix21 = {
				{1,0},
				{3,4}
		};
		
		int[][] expectedInputMatrix2 = {
				{0,0},
				{3,0}
		};
		
		assertArrayEquals(expectedInputMatrix, aas.zeroMatrix(inputMatrix));
		assertArrayEquals(expectedInputMatrix2, aas.zeroMatrix(inputMatrix2));
		
		assertArrayEquals(expectedInputMatrix, aas.zeroMatrix_betterSpaceComplexity(inputMatrix1));
		assertArrayEquals(expectedInputMatrix2, aas.zeroMatrix_betterSpaceComplexity(inputMatrix21));
//		
	}
	
	@Test
	public void Question9_isSubstring() {
		String input = "waterbottle";
		String input2 = "erbottlewat";
		
		String input3 = "elephant";
		String input4 = "anteleph";
		
		String input7 = "ed";
		String input8 = "de";
		
		String input5 = "elephant";
		String input6 = "anreleph";
		
       assertTrue(aas.isSubstring(input, input2));
       assertTrue(aas.isSubstring(input3, input4));
       assertTrue(aas.isSubstring(input7, input8));
       assertFalse(aas.isSubstring(input5, input6));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
