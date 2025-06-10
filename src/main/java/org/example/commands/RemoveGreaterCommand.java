package org.example.commands;

import org.example.data.Organization;
import org.example.data.Validator;
import org.example.files.DataErrorException;
import org.example.managers.AuthorizationManager;

import java.util.List;

import static org.example.Main.clm;

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

    /**
     * Метод реализует команду remove_greater
     *
     * @param args содержит в себе имя команды и аргумент - id организации для сравнения с другими организациями и удалением больших, чем с переданным id
     * @throws CommandException любая ошибка в работе команды обёртывается в CommandException и пробрасывается далее
     */
    @Override
    public void execute(String[] args) throws CommandException {
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
            org = clm.getOrgById(id);
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        }
        //Для последующего лямбда выражения создаю переменную
        Organization finalOrg = org;
        //Нахожу организации, которые больше, чем указанная
        List<Organization> list = clm.getOrgSet().stream().filter(organization -> organization.compareTo(finalOrg) > 0).filter(organization ->
                AuthorizationManager.currentUserId == organization.getOwnerId()).toList();
        //Удаляю найденные организации
        for (Organization organization : list) {
            clm.getOrgSet().remove(organization);
        }
        System.out.println("Было удалено " + list.size() + " организаций");
    }
}
