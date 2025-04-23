package org.example.commands;

public class ExitCommand extends BaseCommand{
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        System.exit(1);
        return null;
    }
}
