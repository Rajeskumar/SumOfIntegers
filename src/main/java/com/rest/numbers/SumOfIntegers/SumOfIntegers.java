package com.rest.numbers.SumOfIntegers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

/**
 * Class that implements sum of Integers alogrithm.
 * It consumes output of an http get end point and calculate the sum of numbers for each object and total sum of summed object numbers
 * and output the same in standard out.
 */
public class SumOfIntegers 
{
	private static final Logger LOGGER = Logger.getLogger(SumOfIntegers.class.getName());
	
	public static void main(String args[]) {
		//Update the Http end url in config.properties before running the main method.
		doSumOfIntegers();
	}
	
	/**
	 * Method that does the full working for Sum of Integers flow.
	 * Gets the http url from Properties file, consume the json from url and then does the sumInteger calculation.
	 * This is entry point for the flow.
	 */
	public static void doSumOfIntegers() {
		String httpUrl = readUrlInput();
		int totalSum = sumIntegers(consumeHttpGet(httpUrl));
		System.out.println("Total Sum :"+totalSum);
	}
	
	/**
	 * Method to read http url end point from properties file
	 */
	public static String readUrlInput() {
		Properties prop = new Properties();
		String url = "";
		try {
			InputStream propFile = new FileInputStream("config.properties");
			prop.load(propFile);
			url=prop.getProperty("httpEndPointUrl");
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, "Exception reading config properties : "+e.getMessage(), e.getCause());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE,"Exception reading config properties : "+ e.getMessage(), e.getCause());
		}
		
		return url;
	}
	
	/**
	 * Method that does sum of all the integers in each Number object and Total sum of all object as well.
	 * @param numberArr
	 * @return
	 */
    public static int sumIntegers(List<Numbers> numberArr) {
    	
    	int totalSum = 0;
    	
    	if(null!=numberArr && ! numberArr.isEmpty()) {
    		for(Numbers numbObj:numberArr) {
        		int sum = Arrays.stream(numbObj.getNumbers()).reduce(0,(n1,n2)->n1+n2);
        		System.out.println("Sum : "+sum);
        		totalSum+=sum;
        	}
    	}else {
    		LOGGER.log(Level.SEVERE, "Numbers Object Null. Please check the HTTP Get URL.");
    	}    	
    	
    	return totalSum;
    }
    
    /**
     * Method to consume HTTP End point url to get Json object
     * @param httpEndPoint
     * @return
     */
    public static List<Numbers> consumeHttpGet(String httpEndPoint){
    	List<Numbers> output = null;
    	StringBuilder sb = new StringBuilder();
    	try {
			URL url = new URL(httpEndPoint);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
			
		    int c;
		    while ((c = reader.read()) != -1) {
		      sb.append((char) c);
		    }
		    
		    output = getNumberObjFromJson(sb.toString());
		    
		} catch (MalformedURLException e) {
			LOGGER.log(Level.SEVERE, "Exception consuming HTTP Get end point : "+ e.getMessage(), e.getCause());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE,"Exception consuming HTTP Get end point : "+ e.getMessage(), e.getCause());
		} catch (JsonParseException e) {
			LOGGER.log(Level.SEVERE,"Exception parsing Json : "+ e.getMessage(), e.getCause());
		}
    	
    	return output;
    }
    
    /**
     * Converts the Json String to Java Object
     * @param string
     * @return
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * @throws JSONException 
     */
	public static List<Numbers> getNumberObjFromJson(String jsonStr) throws JsonParseException {
		return new Gson().fromJson(jsonStr, new TypeToken<List<Numbers>>(){}.getType());
	}
}