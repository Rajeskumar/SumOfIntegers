/**
 * 
 */
package com.rest.numbers.SumOfIntegers;

import java.util.Arrays;

/**
 * @author Rajeshkumar
 *
 */
public class Numbers {

	private String key;
	
	private int[] numbers;
	
	public Numbers() {
		
	}

	public Numbers(String key, int[] orderNo) {
		this.key = key;
		this.numbers = orderNo;
	}

	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int[] getNumbers() {
		return numbers;
	}

	public void setNumbers(int[] numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "Numbers [Key=" + key + ", numbers=" + Arrays.toString(numbers) + "]";
	}
}
