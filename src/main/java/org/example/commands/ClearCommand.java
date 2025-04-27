package org.example.commands;

import org.example.managers.CollectionManager;

/**
 * Команда clear
 */
public class ClearCommand extends BaseCommand{
    /**
     * Конструктор
     */
    public ClearCommand() {
        super("clear","очистить коллекцию",new String[]{});
    }

    /**
     * Метод, реализующий команду
     * @param args
     * @return
     */
    @Override
    public String execute(String[] args) {
        CollectionManager.clearCollection();
        System.out.println("Коллекция очищена");
        return null;
    }
}
