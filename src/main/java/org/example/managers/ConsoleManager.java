package org.example.managers;

import org.example.commands.CommandException;
import org.example.commands.Invoker;
import org.example.data.DataCollector;
import org.example.data.IdManager;
import org.example.data.Validator;
import org.example.files.DataErrorException;
import org.example.files.DataParser;
import org.example.files.FileErrorException;
import org.example.files.PathManager;

import java.io.InputStream;
import java.util.*;

/**
 * Менеджер для работы с консолью
 */
public class ConsoleManager {

    /**
     * Считывает и выполняет команду
     *
     * @param is - поток данных, с которого будут читаться команды
     */
    public void toStart(InputStream is) {
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()) {
            String line;
            try {
                line = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Не было получено строки");
                return;
            }
            String[] tokens = Arrays.stream(line.split(" "))
                    .filter(s -> !(s == null || s.isEmpty())).toArray(String[]::new);
            try {
                //логика для add
                if (tokens[0].equals("add")) {
                    String[] data = DataCollector.collectDataFromConsole(scanner);
                    Invoker.getCommands().get("add").execute(data);
                    continue;
                }
                //логика для add_if_max
                if (tokens[0].equals("add_if_max")) {
                    String[] data = DataCollector.collectDataFromConsole(scanner);
                    Invoker.getCommands().get("add_if_max").execute(data);
                    continue;
                }
                //логика для update
                if (tokens[0].equals("update")) {
                    //проверяем наличие аргументов
                    try {
                        Validator.checkArgs(tokens);
                        if (!IdManager.checkId(Long.valueOf(tokens[1]))) {
                            throw new DataErrorException("Указанный ID не содержится в коллекции");
                        }
                        if(DatabaseManager.canEditOrganization(AuthorizationManager.currentUserId, Long.valueOf(tokens[1]))){
                            throw new CommandException("У Вас нет доступа к организации с id: " + tokens[1]);
                        }
                    } catch (DataErrorException e) {
                        throw new CommandException(e.getMessage());
                    }
                    String[] data = new String[13];
                    //записываю переданный ID в массив
                    data[0] = tokens[1];
                    //Получаю данные для объекта
                    String[] data_2 = DataCollector.collectDataFromConsole(scanner);
                    //копирую данные
                    for (int i = 0; i < data_2.length; i++) {
                        data[i + 1] = data_2[i];  // Начинаем с индекса 1 в data
                    }
                    //Вызываю команду
                    Invoker.getCommands().get("update").execute(data);
                } else {
                    Invoker.execute(tokens);
                }
            } catch (CommandException e) {
                System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("не нажимай enter, пожалуйста");
            }
        }
    }

}
