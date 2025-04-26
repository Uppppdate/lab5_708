package org.example.files;

import org.example.collection.CollectionManager;
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
        //проверяю наличие объектов с нулевым ID с помощью потоков
        List<Organization> list = CollectionManager.getOrgSet().stream().filter(org -> org.getId()==0).toList();
        //удаляю каждый найденный объект из коллекции
        for (Organization org : list){
            CollectionManager.getOrgSet().remove(org);
        }
        //перезаписываю файл без объектов с нулевым ID
        DataWriter.toSave();
        //закрываю поток сканера, считывавшего файл
        scanner.close();

    }
}
