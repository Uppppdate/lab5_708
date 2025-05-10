package org.example.commands;

import org.example.data.Organization;

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
     * Метод, реализующий команду
     *
     * @param args
     * @return
     */
    @Override
    public String execute(String[] args) {
        //Получаю энтри каждой команды из списка команд
        for (Map.Entry<String, BaseCommand> entry : Invoker.getCommands().entrySet()) {
            //Получаю из энтри значение конкретной команды и вывожу помощь по ней в консоль
            System.out.println(entry.getValue().getHelp());
        }
        return null;
    }
}
