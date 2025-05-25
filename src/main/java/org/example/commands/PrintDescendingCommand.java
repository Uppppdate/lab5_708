package org.example.commands;

import java.util.Comparator;

import static org.example.Main.clm;

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
     * Метод, реализующий команду print_descending
     *
     * @param args не используется
     */
    @Override
    public void execute(String[] args) {
        //Получаю коллекцию, сортирую в обратном порядке, вывожу каждый в консоль
        //Порядок определяется методом compareTo() в классе Organization
        clm.getOrgSet().stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}
