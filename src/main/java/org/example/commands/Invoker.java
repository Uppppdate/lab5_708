package org.example.commands;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Stack;

public class Invoker {
    private static HashMap<String, BaseCommand> commands = new HashMap<>();

    private static Deque<BaseCommand> history = new ArrayDeque<>();
    private static final Integer HISTORY_MAX = 15;

    public Invoker(){
        commands.put("info", new InfoCommand());
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("history", new HistoryCommand());
        commands.put("show", new ShowCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("average_of_employees_count", new AverageOfEmployeesCountCommand());
        commands.put("print_descending", new PrintDescendingCommand());
    }

    public static void execute(String[] tokens) throws CommandException{
        try {
            BaseCommand command = commands.get(tokens[0]);
            command.execute(tokens);
            addCommandInHistory(command);
        } catch (NullPointerException e){
            throw new CommandException(tokens[0]);
        }
    }

    private static void addCommandInHistory(BaseCommand command){
        if(history.size() < HISTORY_MAX){
            history.addLast(command);
        } else {
            history.removeFirst();
            history.addLast(command);
        }
    }
    public static HashMap<String, BaseCommand> getCommands() {
        return commands;
    }

    public static Deque<BaseCommand> getHistory() {
        return history;
    }
}
