/**
 * 
 */
package com.upenn.cit591.jobmarkets.libs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test for class WordPair
 * @author zhongliu
 *
 */
public class WordPairTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimilarity() {
		String word1 = "oblige";
		String word2 = "oblivion";
		WordPair wp = new WordPair(word1, word2);
		assertEquals(4, wp.getLeftSimilarity());
		assertEquals(1, wp.getRightSimilarity());
	}
	
	@Test
	public void testCommonLetter() {
		String word1 = "committee";
		String word2 = "comet";
		WordPair wp = new WordPair(word1, word2);
		assertEquals(5, wp.getNumberOfCommonLetters());
	}

}
