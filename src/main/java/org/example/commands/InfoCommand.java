package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.files.DataParser;

public class InfoCommand extends BaseCommand{
    public InfoCommand() {
        super("info", "вывести информацию о коллекции (тип, дата инициализации, количество элементов)", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        System.out.println("Коллекция инициализирована: " + DataParser.formatter.format(CollectionManager.getInitializationDate()));
        System.out.println("Количество элементов: " + CollectionManager.getOrgSet().size());
        return null;
    }
}
