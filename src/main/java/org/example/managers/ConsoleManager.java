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
     * Определяет путь к файлу с данными
     *
     * @param inputStream поток данных, с которого будет считан путь к .csv файлу с данными
     */
    public void toDetermineDataPath(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        System.out.println("Введите путь к .csv файлу");
        System.out.println("или напишите 'default' чтобы установить путь по умолчанию");
        while (scanner.hasNext()) {
            String path = scanner.nextLine();
            if (path.equals("default")) {
                PathManager.setCurrentDataPathAsDefault();
                break;
            }
            try {
                PathManager.getFileFromPath(path);
                PathManager.setCurrentDataPath(path);
                break;
            } catch (FileErrorException e) {
                System.out.println(e.getMessage());
            } catch (DataErrorException e) {
                System.out.println("Неправильно передан путь: " + e);
            }
        }
        DataParser.toParse();
    }

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
