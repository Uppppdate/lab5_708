package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.data.Organization;

import java.util.LinkedHashSet;

public class ShowCommand extends BaseCommand {
    public ShowCommand() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        LinkedHashSet<Organization> set = CollectionManager.getOrgSet();
        if (set.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (Organization org : CollectionManager.getOrgSet()) {
                System.out.println(org.toString());
            }
        }
        return null;
    }
}
