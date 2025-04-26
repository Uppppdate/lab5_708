package org.example.files;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathManager {
    public static Path CURRENT_DATA_PATH;
    private static final Path DEFAULT_DATA_PATH = Path.of("src/main/resources/data.csv");
    public static File getFileFromPath(String path) throws FileErrorException, DataErrorException {
        Path filepath = null;
        try {
            filepath = Paths.get(path);
        } catch (InvalidPathException e){
            throw new DataErrorException(path);
        }
        File file = filepath.toFile();
        if (!(file.canRead() & file.canWrite() & file.exists())) {
            throw new FileErrorException(path);
        }
        return file;
    }

    public static void setCurrentDataPath(String path) throws FileErrorException {
        Path path1;
        try {
            path1 = Paths.get(path);
        }catch (InvalidPathException e){
            throw new FileErrorException(path);
        }
        CURRENT_DATA_PATH = path1;
    }

    public static void setCurrentDataPathAsDefault(){
        CURRENT_DATA_PATH = DEFAULT_DATA_PATH;
    }
}
