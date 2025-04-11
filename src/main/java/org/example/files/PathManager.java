package org.example.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class PathManager {

    public static File getFileFromPath(String path) throws InvalidPathException, FileErrorException {
        Path filepath = Paths.get(path);
        File file = filepath.toFile();
        if (!(file.canRead() & file.canWrite() & file.exists())) {
            throw new FileErrorException(path);
        }
        return file;
    }

}
