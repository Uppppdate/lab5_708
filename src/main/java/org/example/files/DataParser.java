package org.example.files;

import org.example.managers.CollectionManager;
import org.example.data.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Класс парсера данных из файла и записи в коллекцию
 */
public class DataParser {

    /**
     * Формат даты
     */
    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Метод, реализующий парсинг файла с коллекцией её запись
     */
    public static void toParse() {
        File file = null;
        try {
            //получаю файл по текущему пути к файлу
            file = PathManager.getFileFromPath(PathManager.CURRENT_DATA_PATH.toString());
        } catch (FileErrorException | DataErrorException e){
            System.out.println(e.getMessage());
            //если не получается, то устанавливаю путь по умолчанию
            PathManager.setCurrentDataPathAsDefault();
            System.out.println("Установлен путь по умолчанию");
        }
        Scanner scanner = null;
        try {
            //читаю сканером файл
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("You got a easter egg!!! This exception is unreal to get");
        }
        //номер строки
        int lineNumber = 0;
        while (scanner.hasNext()) {
            lineNumber++;
            //читаю строку
            String line = scanner.nextLine();
            //разбиваю на токены
            String[] data = line.split(",");
            try {
                //добавляю объект в коллекцию по данным
                CollectionManager.addOrganizationFromData(data);
            } catch (DataErrorException e) {
                //при нахождении ошибки вывожу информацию о номере строки и ошибке в ней
                System.out.println("Ошибка в строке: " + lineNumber + "\n\t" + e.getMessage());
            }
        }
        System.out.println(lineNumber + " строк было прочитано");
        //очищаю коллекцию от объектов с ID 0
        CollectionManager.cleanUpCollection();
        //закрываю поток сканера, считывавшего файл
        scanner.close();

    }
}
