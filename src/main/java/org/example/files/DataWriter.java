package org.example.files;

import org.example.collection.CollectionManager;
import org.example.data.Organization;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DataWriter {
    public static void toSave(){
        File file = PathManager.CURRENT_DATA_PATH.toFile();
        PrintWriter pw;
        try {
            pw = new PrintWriter(file);
            for (Organization org : CollectionManager.getOrgSet()){
                pw.write(org.toCsv());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        pw.close();
    }
}
