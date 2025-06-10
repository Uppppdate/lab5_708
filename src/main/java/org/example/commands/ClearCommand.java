package org.example.commands;

import org.example.data.IdManager;
import org.example.managers.AuthorizationManager;
import org.example.managers.CollectionManager;
import org.example.managers.DatabaseManager;

import java.sql.SQLException;

import static org.example.Main.clm;

/**
 * Команда clear
 */
public class ClearCommand extends BaseCommand {
    /**
     * Конструктор
     */
    public ClearCommand() {
        super("clear", "очистить коллекцию", new String[]{});
    }

    /**
     * Метод, реализующий команду clear
     *
     * @param args - не используется
     */
    @Override
    public void execute(String[] args) {
        try {
            DatabaseManager.clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        clm.update();
        try {
            IdManager.update();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Коллекция очищена");
    }
}
