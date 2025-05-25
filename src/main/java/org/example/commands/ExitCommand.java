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
     * Метод, реализующий команду exit
     *
     * @param args не используется
     */
    @Override
    public void execute(String[] args) {
        System.exit(1);
    }
}
