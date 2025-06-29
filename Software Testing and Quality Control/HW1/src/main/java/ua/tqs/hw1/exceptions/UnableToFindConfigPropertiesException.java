package ua.tqs.hw1.exceptions;

public class UnableToFindConfigPropertiesException extends RuntimeException{
    public UnableToFindConfigPropertiesException(){
        super("Unable to find config.properties");
    }
}
