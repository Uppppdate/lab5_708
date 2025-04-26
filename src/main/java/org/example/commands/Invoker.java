package org.example.commands;

import org.example.data.Validator;
import org.example.files.DataErrorException;

import java.util.*;

public class Invoker {
    private static HashMap<String, BaseCommand> commands = new HashMap<>();
    private static List<String> compoundCommands = new ArrayList<>();
    private static Deque<BaseCommand> history = new ArrayDeque<>();
    private static final Integer HISTORY_MAX = 15;

    public Invoker() {
        //Добавляю команды в hashMap для дальнейшего вызова по имени
        commands.put("info", new InfoCommand());
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("history", new HistoryCommand());
        commands.put("show", new ShowCommand());
        commands.put("clear", new ClearCommand());
        commands.put("save", new SaveCommand());
        commands.put("average_of_employees_count", new AverageOfEmployeesCountCommand());
        commands.put("print_descending", new PrintDescendingCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("filter_contains_name", new FilterContainsNameCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());

        //Добавляю имена команд с аргументами в compoundCommands
        compoundCommands.add("execute_script");
        compoundCommands.add("update");
        compoundCommands.add("remove_by_id");
        compoundCommands.add("remove_greater");
        compoundCommands.add("filter_contains_name");
    }

    public static void execute(String[] tokens) throws CommandException {
        try {
            BaseCommand command = commands.get(tokens[0]);
            if (compoundCommands.contains(command.getName())) {
                Validator.checkArgs(tokens);
            }
            command.execute(tokens);
            addCommandInHistory(command);
        } catch (NullPointerException e) {
            throw new CommandException(tokens[0]);
        } catch (DataErrorException e){
            throw new CommandException(e.getMessage());
        }
    }

    private static void addCommandInHistory(BaseCommand command) {
        if (history.size() < HISTORY_MAX) {
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
