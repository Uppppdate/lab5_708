package org.example.commands;

import org.example.managers.CollectionManager;

import java.util.Comparator;

/**
 * Команда print_descending выводит коллекцию в порядке убывания
 */
public class PrintDescendingCommand extends BaseCommand {
    /**
     * Конструктор
     */
    public PrintDescendingCommand() {
        super("print_descending", "вывести элементы коллекции в порядке убывания", new String[]{});
    }

    /**
     * Метод, реализующий команду
     *
     * @param args
     * @return
     */
    @Override
    public String execute(String[] args) {
        //Получаю коллекцию, сортирую в обратном порядке, вывожу каждый в консоль
        //Порядок определяется методом compareTo() в классе Organization
        CollectionManager.getOrgSet().stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        return null;
    }
}
