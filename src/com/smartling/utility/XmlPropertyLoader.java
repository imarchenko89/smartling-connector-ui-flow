package com.smartling.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// XmlPropertyLoader for loading properties from xml property file.

public class XmlPropertyLoader {

	static final Logger logger = LoggerFactory.getLogger(XmlPropertyLoader.class);

	public static String loadProperty(String propertyName) {
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					"properties.xml");
			props.loadFromXML(fis);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (InvalidPropertiesFormatException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return props.getProperty(propertyName);
	}
}