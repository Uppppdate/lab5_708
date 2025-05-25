package org.example.commands;

import org.example.data.Organization;

import static org.example.Main.clm;

import java.util.List;

/**
 * Команда filter_contains_name
 */
public class FilterContainsNameCommand extends BaseCommand {
    /**
     * Конструктор
     */
    public FilterContainsNameCommand() {
        super("filter_contains_name", "вывести элементы, значение поля name которых содержит заданную подстроку", new String[]{"name"});
    }

    /**
     * Метод, реализующий команду filter_contains_name
     *
     * @param args содержит в себе имя команды и аргумент в виде подстроки, по которой будет осуществлён поиск
     */
    @Override
    public void execute(String[] args) {
        //Получаю лист со всеми объектами, содержащими в названии переданную подстроку
        List<Organization> set = clm.getOrgSet().stream().filter(organization -> organization.getName().contains(args[1])).toList();
        //Проверяю наличие найденных организаций
        if (set.isEmpty()) {
            System.out.println("Не было найдено объектов с такой подстрокой в имени");
        } else {
            //Вывожу в консоль найденные организации
            for (Organization org : set) {
                System.out.println(org.toString());
            }
        }
    }
}
