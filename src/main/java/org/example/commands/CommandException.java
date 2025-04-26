package org.example.commands;

public class CommandException extends Exception{
    public CommandException(String message) {
        super("Ошибка в команде: " + message);
    }
}
