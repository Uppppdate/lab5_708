package org.example;

import org.example.managers.CollectionManager;
import org.example.commands.Invoker;
import org.example.managers.ConsoleManager;

/**
 * Главный класс
 */
public class Main {
    /**
     * Главный метод
     * @param args
     */
    public static void main(String[] args) {
        //Инициализируем менеджер коллекции
        CollectionManager clm = new CollectionManager();
        //Создаём менеджер консоли
        ConsoleManager csm = new ConsoleManager();
        //Иницализируем инвокер
        Invoker inv = new Invoker();
        //Определяем путь к файлу и записываем в переменную
        csm.toDetermineDataPath(args, System.in);
        //Запускаем менеджер консоли
        csm.toStart(args, System.in);
    }
}