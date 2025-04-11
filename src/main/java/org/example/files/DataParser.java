package org.example.files;

import org.example.collection.CollectionManager;
import org.example.data.Validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

//id,name,coordinates_x,coordinates_y,creation_date,annual_turnover,employees_count,type,officialAddress_street,officialAddress_zipCode,officialAddress_town_x,officialAddress_town_y,officialAddress_town_z
public class DataParser implements Parser {
    private static final String DEFAULT_PATH = "src/main/resources/data.csv";
    public static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void toParse(String path) throws ParsingException {
        File file = null;
        try {
            file = PathManager.getFileFromPath(path);
        } catch (InvalidPathException | FileErrorException e){
            file = new File(DEFAULT_PATH);
            throw new ParsingException(e.getMessage());
        } finally {
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
        }
    }
}
