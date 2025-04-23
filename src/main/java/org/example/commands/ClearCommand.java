package org.example.commands;

import org.example.collection.CollectionManager;

public class ClearCommand extends BaseCommand{
    public ClearCommand() {
        super("clear","очистить коллекцию",new String[]{});
    }

    @Override
    public String execute(String[] args) {
        CollectionManager.clearCollection();
        System.out.println("Коллекция очищена");
        return null;
    }
}
