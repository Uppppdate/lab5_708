package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.data.Organization;

import java.util.Comparator;

public class PrintDescendingCommand extends BaseCommand{
    public PrintDescendingCommand() {
        super("print_descending", "вывести элементы коллекции в порядке убывания", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        Comparator<Organization> comparator = Comparator.comparing(Organization::getId);
        CollectionManager.getOrgSet().stream().sorted(comparator).forEach(System.out::println);
        return null;
    }
}
