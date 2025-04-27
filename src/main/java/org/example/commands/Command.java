package org.example.commands;

/**
 * Интерфейс команды для дополнительного обобщения
 */
public interface Command {
    String execute(String[] args) throws CommandException;
}
