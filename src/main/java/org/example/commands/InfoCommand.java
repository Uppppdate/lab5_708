package org.example.commands;

import org.example.files.DataParser;

import static org.example.Main.clm;

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
     * Метод, реализующий команду info
     *
     * @param args не используется
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Коллекция инициализирована: " + DataParser.formatter.format(clm.getInitializationDate()));
        System.out.println("Количество элементов: " + clm.getOrgSet().size());
    }
}
