package com.rest.numbers.SumOfIntegers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.JsonParseException;

/**
 * Unit test for SumOfIntegersTest.
 */
public class SumOfIntegersTest
{
	private static final Logger LOGGER = Logger.getLogger(SumOfIntegersTest.class.getName());
	
	public static void main(String[] args )
    {
		testReadUrlInput();
//		testJsonToObject();
//		testSumOfIntegers();
		testSumOfIntegers(testJsonToObject());
		
        
    }

	/**
	 * Method to test reading input url from properties file.
	 */
	private static void testReadUrlInput() {
		SumOfIntegers sum = new SumOfIntegers();
		System.out.println(sum.readUrlInput());
	}

	/**
	 * 
	 */
	private static List<Numbers> testJsonToObject() {
		String jsonStr = "[{\"key\":\"value\",\"numbers\":[1,2,3,4,10]}, {\"key\":\"value\",\"numbers\":[1,2,3,4,10]}]";
		
		List<Numbers> numList = null;
		SumOfIntegers sum = new SumOfIntegers();
		try {
			numList = sum.getNumberObjFromJson(jsonStr);
			numList.forEach(number-> System.out.println(number.toString()));
		} catch (JsonParseException e) {
			LOGGER.log(Level.SEVERE,"Exception parsing Json : "+ e.getMessage(), e.getCause());
		}
		
		return numList;
	}

	/**
	 * 
	 */
	private static void testSumOfIntegers(List<Numbers> numbersList) {
		/*int[] orderNo = {1,2,3,4,5};
        Numbers no1 = new Numbers("Order1",orderNo);
        Numbers no2 = new Numbers("Order2",orderNo);
        
        List<Numbers> numbersList = new ArrayList<Numbers>();
        
        numbersList.add(no1);
        numbersList.add(no2);*/
        
        System.out.println("Total Sum :"+SumOfIntegers.sumIntegers(numbersList));
	}
}
