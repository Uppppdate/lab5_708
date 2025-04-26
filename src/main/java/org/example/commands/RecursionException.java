package org.example.commands;

public class RecursionException extends Exception{
    public RecursionException(String message) {
        super("Рекурсия в файле: " + message);
    }
}
