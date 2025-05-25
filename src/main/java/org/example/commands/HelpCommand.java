package org.example.commands;

import java.util.Map;

/**
 * Команда help
 */
public class HelpCommand extends BaseCommand {
    /**
     * Конструктор
     */
    public HelpCommand() {
        super("help", "вывести справку по доступным командам ", new String[]{});
    }

    /**
     * Метод, реализующий команду help
     *
     * @param args не используется
     */
    @Override
    public void execute(String[] args) {
        //Получаю энтри каждой команды из списка команд
        for (Map.Entry<String, BaseCommand> entry : Invoker.getCommands().entrySet()) {
            //Получаю из энтри значение конкретной команды и вывожу помощь по ней в консоль
            System.out.println(entry.getValue().getHelp());
        }
    }
}
