package org.example.files;

/**
 * Ошибка, пробрасываемая при любом ошибке при работе с данными
 */
public class DataErrorException extends Exception{
    public DataErrorException(String message) {
        super(message);
    }
}
