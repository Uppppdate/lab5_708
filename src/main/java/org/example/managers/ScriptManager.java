package org.example.managers;

import org.example.commands.CommandException;
import org.example.commands.Invoker;
import org.example.data.DataCollector;
import org.example.data.IdManager;
import org.example.data.Validator;
import org.example.files.DataErrorException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

/**
 * Менеджер для работы со скриптом
 */
public class ScriptManager {
    /**
     * Стэк используемых скриптов
     */
    private static Stack<File> scriptsStack = new Stack<>();

    public static Stack<File> getScriptsStack() {
        return scriptsStack;
    }

    /**
     * Аналог метода toStart в console manager'е, только для работы в скрипте
     * @see ConsoleManager#toStart(String[], InputStream) 
     * @param args 
     * @param is
     */
    public void toStart(String[] args, FileInputStream is) {
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()) {
            String line;
            try {
                line = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("No line found");
                return;
            }
            String[] tokens = Arrays.stream(line.split(" "))
                    .filter(s -> !(s == null || s.isEmpty())).toArray(String[]::new);
            try {
                //логика для add
                if (tokens[0].equals("add")) {
                    String[] data = DataCollector.collectDataFromScript(scanner);
                    Invoker.getCommands().get("add").execute(data);
                }
                //логика для add_if_max
                if (tokens[0].equals("add_if_max")) {
                    String[] data = DataCollector.collectDataFromScript(scanner);
                    Invoker.getCommands().get("add_if_max").execute(data);
                }
                //логика для update
                if (tokens[0].equals("update")) {
                    //проверяем наличие аргументов
                    try {
                        Validator.checkArgs(tokens);
                        if (!IdManager.checkId(Long.valueOf(tokens[1]))){
                            throw new DataErrorException("Указанный ID не содержится в коллекции");
                        }
                    } catch (DataErrorException e) {
                        throw new CommandException(e.getMessage());
                    }
                    String[] data = new String[13];
                    //записываю переданный ID в массив
                    data[0] = tokens[1];
                    //Получаю данные для объекта
                    String[] data_2 = DataCollector.collectDataFromScript(scanner);
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
                System.out.println("Пустая строка");
            }
        }
    }
}
