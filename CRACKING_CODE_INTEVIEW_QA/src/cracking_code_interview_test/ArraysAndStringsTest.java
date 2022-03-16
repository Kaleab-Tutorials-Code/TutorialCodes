package cracking_code_interview_test;

import static org.junit.Assert.assertEquals;

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
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
