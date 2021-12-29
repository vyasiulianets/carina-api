package org.vyasiulianets.carinaapi.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

	public static Properties getPropertiesFromFile(String fileName) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(fileName));
		} catch (FileNotFoundException fileNotFoundException) {
			throw new RuntimeException("File not found. Please check file exists.", fileNotFoundException);
		} catch (IOException ioException) {
			throw new RuntimeException("Something went wrong while loading properties.", ioException);
		}
		return properties;
	}
}
