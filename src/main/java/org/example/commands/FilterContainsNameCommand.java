package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.data.Organization;
import org.example.data.Validator;
import org.example.files.DataErrorException;

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
     * Метод, реализующий команду
     *
     * @param args
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(String[] args) throws CommandException {
        //Получаю лист со всеми объектами, содержащими в названии переданную подстроку
        List<Organization> set = CollectionManager.getOrgSet().stream().filter(organization -> organization.getName().contains(args[1])).toList();
        //Проверяю наличие найденных организаций
        if (set.isEmpty()) {
            System.out.println("Не было найдено объектов с такой подстрокой в имени");
        } else {
            //Вывожу в консоль найденные организации
            for (Organization org : CollectionManager.getOrgSet()) {
                System.out.println(org.toString());
            }
        }
        return null;
    }
}
