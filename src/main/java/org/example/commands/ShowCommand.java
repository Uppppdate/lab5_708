package org.example.commands;

import org.example.data.Organization;

import java.util.LinkedHashSet;

import static org.example.Main.clm;

/**
 * Команда show выводит коллекцию в консоль
 */
public class ShowCommand extends BaseCommand {
    public ShowCommand() {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", new String[]{});
    }

    /**
     * Метод реализует команду show
     * @param args - не используется
     */
    @Override
    public void execute(String[] args) {
        clm.update();
        //Получаю коллекцию
        LinkedHashSet<Organization> set = clm.getOrgSet();
        //Проверяю на наличие элементов
        if (set.isEmpty()) {
            System.out.println("Коллекция пуста");
        } else {
            for (Organization org : clm.getOrgSet()) {
                System.out.println(org.toString());
            }
        }
    }
}
