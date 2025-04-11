package org.example.files;

public class ParsingException extends Exception{
    public ParsingException(String message) {
        super("Error with parser:\n\t"+message);
    }
}
