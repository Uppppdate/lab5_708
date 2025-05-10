package org.example.files;

import org.example.managers.CollectionManager;
import org.example.data.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Класс, сохраняющий коллекцию в файл
 */
public class DataWriter {
    public static void toSave() {
        //получаю файл по текущему пути
        File file = PathManager.CURRENT_DATA_PATH.toFile();
        PrintWriter pw;
        try {
            //передаю файл в printWriter
            pw = new PrintWriter(file);
            //перебираю все объекты в коллекции
            for (Organization org : CollectionManager.getOrgSet()) {
                //привожу в вид для записи в csv и записываю
                pw.write(org.toCsv());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pw.close();
    }
}
