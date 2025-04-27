package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.data.Organization;

import java.util.LinkedHashSet;

/**
 * Команда show выводит коллекцию в консоль
 */
public class ShowCommand extends BaseCommand {
    public ShowCommand() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        //Получаю коллекцию
        LinkedHashSet<Organization> set = CollectionManager.getOrgSet();
        //Проверяю на наличие элементов
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
