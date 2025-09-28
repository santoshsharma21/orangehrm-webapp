/**
 * 
 */
package com.orangehrm.utils;

import com.github.javafaker.Faker;
import com.orangehrm.enums.RandomDataConstants;

/**
 * 
 */
public class FakerUtils {
	
	// java faker instance
	private static final Faker faker = new Faker();
	
	// get data
	public static String getRandomData(RandomDataConstants data) {
		switch(data) {
		case FIRSTNAME:
			return faker.name().firstName();
		case MIDDLENAME:
			return faker.name().prefix();
		case LASTNAME:
			return faker.name().lastName();
		default:
			return "data not available";
		}
	}
}
