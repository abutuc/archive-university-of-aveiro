package ua.tqs.hw1.exceptions;

public class UnableToGetConfigPropertiesException extends RuntimeException {
    public UnableToGetConfigPropertiesException(){
        super("unable to get properties from config");
    }
}
