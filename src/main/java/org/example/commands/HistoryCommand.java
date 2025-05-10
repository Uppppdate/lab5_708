package org.example.commands;

/**
 * Команда history
 * Выводит 15 последних команд в консоль
 */
public class HistoryCommand extends BaseCommand {
    /**
     * Конструктор
     */
    public HistoryCommand() {
        super("history", "вывести последние 15 команд (без их аргументов)", new String[]{});
    }

    /**
     * Метод, реализующий команду
     *
     * @param args
     * @return
     */
    @Override
    public String execute(String[] args) {
        //Счётчик для вывода команд с нумерацией
        int count = 1;
        //Получаю историю команд
        for (BaseCommand command : Invoker.getHistory()) {
            //Вывожу в нужном виде
            System.out.println(count + ". " + command.getName());
            count++;
        }
        return null;
    }
}
