package org.example.commands;

/**
 * Ошибка, которая используется при любой ошибке, возникшей в программе
 */
public class CommandException extends Exception{
    public CommandException(String message) {
        super("Ошибка в команде: " + message);
    }
}
