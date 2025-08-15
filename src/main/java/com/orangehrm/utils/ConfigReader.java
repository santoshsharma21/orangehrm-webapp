/**
 * 
 */
package com.orangehrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;

/**
 * Loads the configuration file.
 */
public class ConfigReader {

	// instance of Properties
	private static final Properties prop = new Properties();
	
	// instance of Logger
	private static final Logger log = LoggerManager.getLogger(ConfigReader.class);

	// static initializer block
	static {
		loadConfig();
	}
	
	/**
	 * Loads the config.propertise file from resources folder.
	 */
	private static void loadConfig() {
		String filePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
		File configFile = new File(filePath);
		if(!configFile.exists()) {
			log.error("config file not found in the resource folder.");
			throw new RuntimeException("Config file not found: " + configFile.getAbsolutePath());
		}
		
		try(FileInputStream fis = new FileInputStream(configFile)) {
			prop.load(fis);
			log.info("Successfully loaded config file.");
		} catch(IOException e) {
			log.error("Failed to load config file: " + e.getMessage());
			throw new RuntimeException("Failed to load config file: " + configFile.getAbsolutePath(), e);
		}
	}

	/**
	 * Returns the value associated with key from config file.
	 * 
	 * @param key key whose value is to be retrieved.
	 * @return value associated with the given key.
	 * @throws throw RuntimeException if key not found in config file.
	 */
	public static String getProperty(String key) {
		String value = prop.getProperty(key);
		if(value == null) {
			log.warn("Value for key '" + key + "' is null or not found.");
			throw new RuntimeException("Key not found in config file: " + key);
		}
		return value;
	}
}
