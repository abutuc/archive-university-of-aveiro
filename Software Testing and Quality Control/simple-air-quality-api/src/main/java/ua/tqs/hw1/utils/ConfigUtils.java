package ua.tqs.hw1.utils;

import ua.tqs.hw1.exceptions.UnableToFindConfigPropertiesException;
import ua.tqs.hw1.exceptions.UnableToGetConfigPropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {

    private ConfigUtils(){
        throw new IllegalStateException("Utility class");
    }

    public static String getPropertyFromConfig(String property) {

        try (InputStream input = ConfigUtils.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                throw new UnableToFindConfigPropertiesException();
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and print it out
            return prop.getProperty(property);

        } catch (IOException ex) {
            throw new UnableToGetConfigPropertiesException();
        }
    }
}
