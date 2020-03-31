package com.upenn.cit591.jobmarkets.libs;

public class WordPair {

	private String word1;
	private String originalWord1;
	private String word2;
	private String originalWord2;
	public String getOriginalWord1() {
		return originalWord1;
	}

	public String getOriginalWord2() {
		return originalWord2;
	}


	private int leftSimilarity;
	private int rightSimilarity;
	private double similarity;
	private double commonPercent;
	private int numberOfCommonLetters;
	
	private char[] word1Chars;
	private char[] word2Chars;
	
	
	public WordPair(String word1, String word2) {
		this.originalWord1 = word1;
		this.originalWord2 = word2;
		this.word1 = word1.toLowerCase();
		this.word2 = word2.toLowerCase();
		this.word1Chars = this.word1.toCharArray();
		this.word2Chars = this.word2.toCharArray();
		this.calculateLeftSimilarity();
		this.calculateRightSimilarity();
		this.calculateSimilarity();
		this.calculateNumberOfCommonLetters();
		this.calculateCommonPercent();
	}
	
	/**
	 * leftSimilarity (a made-up metric) – 
	 * the number of letters that match up between word1 and word2 as we go from left to right and compare character by character.
	 * So the leftSimilarity for ’oblige’ and ’oblivion’ is 4, the leftSimilarity for ’aghast’ and ’gross’ is 1.
	 */
	private void calculateLeftSimilarity() {
		this.leftSimilarity=0;
		for(int i=0;i<this.word1Chars.length && i < this.word2Chars.length;i++) {
			if(word1Chars[i]==word2Chars[i]) {
				this.leftSimilarity++;
			}
		}
	}
	
	
	/**
	 * Similar to calculateLeftSimilarity, this is to calculate rightSimilarity (another made-up metric) – 
	 * the number of letters that match up, but this time going from right to left.
	 */
	private void calculateRightSimilarity() {
		this.rightSimilarity=0;
		int index1 = this.word1Chars.length-1;
		int index2 = this.word2Chars.length-1;
		for(int i=0;i<this.word1Chars.length && i < this.word2Chars.length;i++) {
			if(word1Chars[index1 - i]==word2Chars[index2 - i]) {
				this.rightSimilarity++;
			}
		}
				
	}
	
	/**
	 * calculate similarity % 
	 */
	private void calculateSimilarity() {
		this.similarity = (this.getLeftSimilarity()+this.getRightSimilarity())/2.0;
	}
	
	/**
	 * We will define commonPercent mathematically as follows: 
	 * Consider two words w1 and w2. Create the set of letters in w1, 
	 * call that S1 (remember it is a set so repeated letters show up only once). 
	 * Create the set of letters in w2, call that S2. Then commonPercent is defined as
	 * 		Number of elements in S1 intersected with S2 / Number of elements in S1 union S2. Or
	 *		Number of letters that are common across the two sets (Set 1 and Set 2) divided by the Total number of letters in Set 1 and Set 2 (with each letter counting exactly once).
	 */
	private void calculateCommonPercent() {
		
		char[] totalUniqueChars = this.uniqueChars((this.word1+this.word2).toCharArray());
		if(this.numberOfCommonLetters==0) {
			this.calculateNumberOfCommonLetters();
		}
		
		this.commonPercent = this.numberOfCommonLetters/(double)totalUniqueChars.length;
	}
	
	private char[] uniqueChars(char[] chars) {
		String uniqueChars = "";
		for (int i=0;i<chars.length;i++) {
			String toBeAddedChar = String.valueOf(chars[i]);
			if(uniqueChars.indexOf(toBeAddedChar)==-1) {
				uniqueChars+=toBeAddedChar;
			}
		}
		return uniqueChars.toCharArray();
	}
	
	/**
	 *  calculate Number of letters that are common across the two words in the pair. 
	 */
	private void calculateNumberOfCommonLetters() {
		char[] word1UniqueChars = this.uniqueChars(this.word1Chars);
		char[] word2UniqueChars = this.uniqueChars(this.word2Chars);
		this.numberOfCommonLetters = 0;
		for(int i=0;i<word1UniqueChars.length;i++) {
			for(int j=0;j<word2UniqueChars.length;j++) {
				if (word1UniqueChars[i]==word2UniqueChars[j]) {
					this.numberOfCommonLetters++;
					break;
				}
			}
		}
		
	}
	
	
	public int getLeftSimilarity() {
		return leftSimilarity;
	}
	public int getRightSimilarity() {
		return rightSimilarity;
	}
	public double getSimilarity() {
		return similarity;
	}
	public double getCommonPercent() {
		return this.commonPercent;
	}
	public int getNumberOfCommonLetters() {
		return numberOfCommonLetters;
	}
	
	public String getWord1() {
		return this.word1;
	}
	public String getWord2() {
		return this.word2;
	}
	
	
	public static void main(String[] args) {
		String word1 = "oblige";
		String word2 = "oblivion";
		WordPair wp = new WordPair(word1, word2);
		System.out.println("Testing similarity '"+word1+"','"+word2+"'");
		System.out.println("LeftSimilarity should be 4, actual is "+wp.getLeftSimilarity());
		System.out.println("RightSimilarity should be 1, actual is "+wp.getRightSimilarity());
		System.out.println("Similarity should be 2.5, actual is "+wp.getSimilarity());
		
		
		word1 = "aghast";
		word2 = "gross";
		System.out.println("Testing similarity '"+word1+"','"+word2+"'");
		wp = new WordPair(word1, word2);
		System.out.println("LeftSimilarity should be 1, actual is "+wp.getLeftSimilarity());
		System.out.println("RightSimilarity should be 2, actual is "+wp.getRightSimilarity());
		System.out.println("Similarity should be 1.5, actual is "+wp.getSimilarity());
		
		
		word1 = "committee";
		word2 = "comet";
		System.out.println("Testing commonality '"+word1+"','"+word2+"'");
		wp = new WordPair(word1, word2);
		System.out.println("NumberOfCommonLetters should be 5, actual is "+wp.getNumberOfCommonLetters());
		System.out.println("CommonPercent should be 5/6=0.83333, actual is "+wp.getCommonPercent());
		
	}

}
