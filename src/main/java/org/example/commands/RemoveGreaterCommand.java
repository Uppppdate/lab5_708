package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.data.Organization;
import org.example.data.Validator;
import org.example.files.DataErrorException;

import java.util.List;

/**
 * Команда remove_greater id удаляет из коллекции элементы, превышающие элемент с заданным ID
 */
public class RemoveGreaterCommand extends BaseCommand {
    /**
     * Конструктор
     */
    public RemoveGreaterCommand() {
        super("remove_greater", "удалить из коллекции все элементы, превышающие заданный", new String[]{"id"});
    }

    @Override
    public String execute(String[] args) throws CommandException {
        //объявляю организацию
        Organization org = null;
        try {
            //проверяю на наличие аргументов для команды
            Validator.checkArgs(args);
            //проверяю правильность введенных аргументов
            Validator.checkLong(args[1]);
            //Считываю аргумент
            Long id = Long.valueOf(args[1]);
            //Нахожу организацию по ID
            org = CollectionManager.getOrgById(id);
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        }
        //Для последующего лямбда выражения создаю переменную
        Organization finalOrg = org;
        //Нахожу организации, которые больше, чем указанная
        List<Organization> list = CollectionManager.getOrgSet().stream().filter(organization -> organization.compareTo(finalOrg) > 0).toList();
        //Удаляю найденные организации
        for (Organization organization : list) {
            CollectionManager.getOrgSet().remove(organization);
        }
        System.out.println("Было удалено " + list.size() + " организаций");
        return null;
    }
}
