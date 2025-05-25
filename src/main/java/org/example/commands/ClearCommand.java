package org.example.commands;

import org.example.managers.CollectionManager;
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
        clm.clearCollection();
        System.out.println("Коллекция очищена");
    }
}
