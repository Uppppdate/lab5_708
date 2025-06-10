package org.example;

import org.example.data.Validator;
import org.example.files.DataErrorException;
import org.example.managers.*;
import org.example.commands.Invoker;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Главный класс
 */
public class Main {
    public static CollectionManager clm;

    /**
     * Главный метод
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) throws SQLException {
        //Инициализируем менеджер коллекции
        clm = new CollectionManager();
        //Создаём менеджер консоли
        ConsoleManager csm = new ConsoleManager();
        //Авторизация
        AuthorizationManager aum = new AuthorizationManager();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Введите команду:");
                System.out.println("register - зарегистрироваться");
                System.out.println("login - войти в аккаунт");
                String command = scanner.nextLine();
                Validator.checkName(command);
                if (command.equals("login")) {
                    aum.toAuthorize();
                }
                if (command.equals("register")) {
                    aum.toRegister();
                }
                break;
            } catch (AuthorizationException e) {
                System.out.println(e.getMessage());
            } catch (DataErrorException e) {
                System.out.println("Неверная команда");
            }
        }
        //Запускаем менеджер консоли
        csm.toStart(System.in);
    }
}