package org.example.console;

import org.example.commands.CommandException;
import org.example.commands.Invoker;
import org.example.files.DataParser;
import org.example.files.FileErrorException;
import org.example.files.PathManager;

import java.io.InputStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ConsoleManager {
    public void toDetermineDataPath(String[] args, InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        System.out.println("Enter path to .csv file");
        System.out.println("or type 'default' to set path as default");
        while (scanner.hasNext()) {
            String path = scanner.nextLine();
            if (path.equals("default")) {
                PathManager.setCurrentDataPathAsDefault();
                break;
            }
            try {
                PathManager.getFileFromPath(path);
                PathManager.setCurrentDataPath(path);
                break;
            } catch (FileErrorException e) {
                System.out.println(e.getMessage());
            }
        }
        DataParser.toParse();
    }

    public void toStart(String[] args, InputStream is) {
        Scanner scanner = new Scanner(is);
        while (true) {
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
                Invoker.execute(tokens);
            } catch (CommandException e){
                System.out.println(e.getMessage());
            }
        }
    }

}
