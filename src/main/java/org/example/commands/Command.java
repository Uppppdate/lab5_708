package org.example.commands;

/**
 * Интерфейс команды для дополнительного обобщения
 */
public interface Command {
    void execute(String[] args) throws CommandException;
}
