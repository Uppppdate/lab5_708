package org.example.commands;

import org.example.files.DataErrorException;

import static org.example.Main.clm;

/**
 * Команда update id позволяет перезаписать значение объекта с заданным ID
 */
public class UpdateCommand extends BaseCommand {
    public UpdateCommand() {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", new String[]{"id"});
    }

    /**
     * Метод реализует команду update
     *
     * @param args массив с полными данными для создания организации
     * @throws CommandException любая ошибка в работе команды обёртывается в CommandException и пробрасывается далее
     */
    @Override
    public void execute(String[] args) throws CommandException {
        try {
            //Удаляю организацию с переданным ID
            clm.removeOrganization(Long.valueOf(args[0]));
            //Добавляю организацию с переданным ID
            clm.addOrganizationFromData(args);
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
