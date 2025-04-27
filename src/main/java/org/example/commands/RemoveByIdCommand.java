package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.data.Validator;
import org.example.files.DataErrorException;

/**
 * Команда remove_by_id id удаляет организацию с заданным ID
 */
public class RemoveByIdCommand extends BaseCommand {
    /**
     * Конструктор
     */
    public RemoveByIdCommand() {
        super("remove_by_id", "удалить элемент из коллекции по его id", new String[]{"id"});
    }

    /**
     * Метод, реализующий команду
     * @param args
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(String[] args) throws CommandException {
        try {
            //Проверяю аргументы
            Validator.checkLong(args[1]);
        } catch (DataErrorException e) {
            throw new CommandException("Неправильно указан ID");
        }
        try {
            //Удаляю организацию с заданным ID
            CollectionManager.removeOrganization(Long.valueOf(args[1]));
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        }
        System.out.println("Удалена организация с ID: " + args[1]);
        return null;
    }
}
