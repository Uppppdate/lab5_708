package org.example.commands;

import org.example.data.Validator;
import org.example.files.DataErrorException;
import org.example.managers.AuthorizationManager;
import org.example.managers.DatabaseManager;

import java.sql.SQLException;

import static org.example.Main.clm;

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
     * Метод, реализующий команду remove_by_id
     *
     * @param args содержит в себе имя команды и аргумент - id организации для удаления
     * @throws CommandException любая ошибка в работе команды обёртывается в CommandException и пробрасывается далее
     */
    @Override
    public void execute(String[] args) throws CommandException {
        try {
            //Проверяю аргументы
            Validator.checkLong(args[1]);
            if(DatabaseManager.canEditOrganization(AuthorizationManager.currentUserId, Long.valueOf(args[1]))){
                throw new CommandException("У Вас нет доступа к организации с id: " + args[1]);
            }
        } catch (DataErrorException e) {
            throw new CommandException("Неправильно указан ID");
        }
        try {
            //Удаляю организацию с заданным ID
            clm.removeOrganization(Long.valueOf(args[1]));
            try {
                DatabaseManager.saveTable(AuthorizationManager.currentUserId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        }
        System.out.println("Удалена организация с ID: " + args[1]);
    }
}
