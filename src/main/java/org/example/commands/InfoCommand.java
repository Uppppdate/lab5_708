package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.files.DataParser;

/**
 * Команда info
 * Выводит базовую информацию о коллекции в консоль
 */
public class InfoCommand extends BaseCommand {
    /**
     * Конструктор
     */
    public InfoCommand() {
        super("info", "вывести информацию о коллекции (тип, дата инициализации, количество элементов)", new String[]{});
    }

    /**
     * Метод, реализующий команду
     *
     * @param args
     * @return
     */
    @Override
    public String execute(String[] args) {
        System.out.println("Коллекция инициализирована: " + DataParser.formatter.format(CollectionManager.getInitializationDate()));
        System.out.println("Количество элементов: " + CollectionManager.getOrgSet().size());
        return null;
    }
}
