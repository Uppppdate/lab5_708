package org.example.files;

import org.example.collection.CollectionManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DataParser {

    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void toParse() {
        File file = null;
        try {
            file = PathManager.getFileFromPath(PathManager.CURRENT_DATA_PATH.toString());
        } catch (FileErrorException e){
            System.out.println(e.getMessage());
            PathManager.setCurrentDataPathAsDefault();
            System.out.println("Path was switched to default");
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
                System.out.println("Error in line: " + lineNumber + "\n\t" + e.getMessage());
            }
        }
        System.out.println(lineNumber + " lines was parsed");
        scanner.close();

    }
}
