package org.example.commands;

import org.example.files.DataErrorException;
import org.example.managers.CollectionManager;

/**
 * Команда update id позволяет перезаписать значение объекта с заданным ID
 */
public class UpdateCommand extends BaseCommand {
    public UpdateCommand() {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", new String[]{"id"});
    }

    /**
     * @param args массив с полными данными для создания организации
     * @return
     * @throws CommandException
     */
    @Override
    public String execute(String[] args) throws CommandException {
        try {
            //Удаляю организацию с переданным ID
            CollectionManager.removeOrganization(Long.valueOf(args[0]));
            //Добавляю организацию с переданным ID
            CollectionManager.addOrganizationFromData(args);
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        }
        return null;
    }
}
