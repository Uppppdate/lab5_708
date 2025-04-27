package org.example.files;

import org.example.managers.CollectionManager;
import org.example.data.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class DataParser {

    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void toParse() {
        File file = null;
        try {
            file = PathManager.getFileFromPath(PathManager.CURRENT_DATA_PATH.toString());
        } catch (FileErrorException | DataErrorException e){
            System.out.println(e.getMessage());
            PathManager.setCurrentDataPathAsDefault();
            System.out.println("Установлен путь по умолчанию");
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("You got a easter egg!!! This exception is unreal to get");
        }
        int lineNumber = 0;
        while (scanner.hasNext()) {
            lineNumber++;
            String line = scanner.nextLine();
            String[] data = line.split(",");
            try {
                CollectionManager.addOrganizationFromData(data);
            } catch (DataErrorException e) {
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
