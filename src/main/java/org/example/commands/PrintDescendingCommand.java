package org.example.commands;

import org.example.managers.CollectionManager;

import java.util.Comparator;

public class PrintDescendingCommand extends BaseCommand{
    public PrintDescendingCommand() {
        super("print_descending", "вывести элементы коллекции в порядке убывания", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        CollectionManager.getOrgSet().stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        return null;
    }
}
