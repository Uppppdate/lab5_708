package org.example.commands;

import org.example.files.DataErrorException;
import org.example.managers.AuthorizationManager;
import org.example.managers.CollectionManager;
import org.example.managers.DatabaseManager;

import java.sql.SQLException;
import java.util.Arrays;

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
            String[] args2 = Arrays.copyOfRange(args, 1, args.length);
            DatabaseManager.updateById(Long.parseLong(args[0]), args2, AuthorizationManager.currentUserId);
            clm.update();
        } catch (SQLException | DataErrorException e) {
            throw new RuntimeException(e);
        }
    }
}
