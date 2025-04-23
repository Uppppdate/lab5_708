package org.example.commands;

public class HistoryCommand extends BaseCommand{
    public HistoryCommand() {
        super("history", "вывести последние 15 команд (без их аргументов)", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        int count = 1;
        for(BaseCommand command : Invoker.getHistory()){
            System.out.println(count + ". " + command.getName());
            count++;
        }
        return null;
    }
}
