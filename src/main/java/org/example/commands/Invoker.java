package org.example.commands;

import org.example.data.Validator;
import org.example.files.DataErrorException;

import java.util.*;

/**
 * Класс Invoker хранит в себе все команды, историю вызова команд,
 * вызывает метод execute у нужной команды и передаёт в неё аргументы.
 */
public class Invoker {
    /**
     * Хэш мап со всеми командами, где ключ - имя команды, значение - сама команда
     */
    private static final HashMap<String, BaseCommand> commands = new HashMap<>();
    /**
     * Лист с именами команд с аргументами, необходима для проверки наличия аргументов
     */
    private static final List<String> compoundCommands = new ArrayList<>();

    /**
     * Deque, хранящая последние 15 команд, которые были использованы
     */
    private static final Deque<BaseCommand> history = new ArrayDeque<>();

    /**
     * Константа, определяющая размерность истории команд
     */
    private static final Integer HISTORY_MAX = 15;

    /*
      Статический блок инициализации с объявлением всех команд и помещением их в hashmap
     */
    static {
        //Добавляю команды в hashMap для дальнейшего вызова по имени
        commands.put("info", new InfoCommand());
        commands.put("help", new HelpCommand());
        commands.put("exit", new ExitCommand());
        commands.put("history", new HistoryCommand());
        commands.put("show", new ShowCommand());
        commands.put("clear", new ClearCommand());
        commands.put("average_of_employees_count", new AverageOfEmployeesCountCommand());
        commands.put("print_descending", new PrintDescendingCommand());
        commands.put("execute_script", new ExecuteScriptCommand());
        commands.put("remove_by_id", new RemoveByIdCommand());
        commands.put("filter_contains_name", new FilterContainsNameCommand());
        commands.put("remove_greater", new RemoveGreaterCommand());
        commands.put("add", new AddCommand());
        commands.put("update", new UpdateCommand());
        commands.put("add_if_max", new AddIfMaxCommand());

        //Добавляю имена команд с аргументами в compoundCommands
        compoundCommands.add("execute_script");
        compoundCommands.add("update");
        compoundCommands.add("remove_by_id");
        compoundCommands.add("remove_greater");
        compoundCommands.add("filter_contains_name");
    }


    /**
     * @param tokens токены, на которые была разбита строка с консоли, содержащая команду (и аргументы)
     * @throws CommandException пробрасывается с разным message, при разных ошибках внутри команды
     */
    public static void execute(String[] tokens) throws CommandException {
        try {
            //Нахожу команду в списке команд
            BaseCommand command = commands.get(tokens[0]);
            //Проверяю на наличие команды в списке составных команд
            if (compoundCommands.contains(command.getName())) {
                //Проверяю наличие аргументов
                Validator.checkArgs(tokens);
            }
            //Выполняю команду
            command.execute(tokens);
            //Добавляю команду в историю выполненных команд
            addCommandInHistory(command);
        } catch (NullPointerException e) {
            throw new CommandException(tokens[0]);
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(" ");
        }
    }

    /**
     * Добавляет команду в историю
     *
     * @param command Команда для добавления в историю
     */
    private static void addCommandInHistory(BaseCommand command) {
        //Учитывает максимальный размер истории команд
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
