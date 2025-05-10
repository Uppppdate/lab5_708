package org.example.commands;

/**
 * Команда exit
 */
public class ExitCommand extends BaseCommand {
    /**
     * Конструктор
     */
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)", new String[]{});
    }

    /**
     * Метод, реализующий команду
     *
     * @param args
     * @return
     */
    @Override
    public String execute(String[] args) {
        System.exit(1);
        return null;
    }
}
