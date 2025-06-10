package org.example.files;


import java.text.SimpleDateFormat;

/**
 * Класс парсера данных из файла и записи в коллекцию
 */
public class DataParser {

    /**
     * Формат даты
     */
    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static {
        formatter.setLenient(false); // Запрещаем автоматический пересчёт некорректных дат
    }

}
