package org.example;

import org.example.managers.CollectionManager;
import org.example.commands.Invoker;
import org.example.managers.ConsoleManager;

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
    public static void main(String[] args) {
        //Инициализируем менеджер коллекции
        clm = new CollectionManager();
        //Создаём менеджер консоли
        ConsoleManager csm = new ConsoleManager();
        //Определяем путь к файлу и записываем в переменную
        csm.toDetermineDataPath(System.in);
        //Запускаем менеджер консоли
        csm.toStart(System.in);
    }
}