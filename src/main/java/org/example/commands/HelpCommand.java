package org.example.commands;

import org.example.data.Organization;

import java.util.Map;

public class HelpCommand extends BaseCommand{
    public HelpCommand() {
        super("help", "вывести справку по доступным командам ", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        for(Map.Entry<String, BaseCommand> entry:  Invoker.getCommands().entrySet()){
            System.out.println(entry.getValue().getHelp());
        }
        return null;
    }
}
