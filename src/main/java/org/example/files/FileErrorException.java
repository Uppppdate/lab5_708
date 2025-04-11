package org.example.files;

public class FileErrorException extends Exception{
    public FileErrorException(String path) {
        super("Error with file on path: " + path);
    }
}
