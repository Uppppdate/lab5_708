package org.example.files;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс для работы с путями и их хранения
 */
public class PathManager {
    /**
     * Путь до текущего файла с данными
     */
    public static Path CURRENT_DATA_PATH;
    /**
     * Путь до файлов с данными по умолчанию
     */
    private static final Path DEFAULT_DATA_PATH = Path.of("src/main/resources/data.csv");

    /**
     * Метод, возвращающий файл по данному пути, если он существует и доступен к использованию
     *
     * @param path путь
     * @return файл по переданному пути
     * @throws FileErrorException При невозможности прочитать файл, записать в файл, а также если файл не существует
     * @throws DataErrorException При невозможности конвертировать переданную строку в путь
     */
    public static File getFileFromPath(String path) throws FileErrorException, DataErrorException {
        Path filepath = null;
        try {
            filepath = Paths.get(path);
        } catch (InvalidPathException e) {
            throw new DataErrorException(path);
        }
        File file = filepath.toFile();
        if (!(file.canRead() & file.canWrite() & file.exists())) {
            throw new FileErrorException(path);
        }
        return file;
    }

    /**
     * Устанавливает текущий путь до файла с данными при правильном переданном пути
     *
     * @param path путь до файла
     * @throws FileErrorException пробрасывается, если путь неверный
     */
    public static void setCurrentDataPath(String path) throws FileErrorException {
        Path path1;
        try {
            path1 = Paths.get(path);
        } catch (InvalidPathException e) {
            throw new FileErrorException(path);
        }
        CURRENT_DATA_PATH = path1;
    }

    /**
     * Устанавливает значение пути по умолчанию
     */
    public static void setCurrentDataPathAsDefault() {
        CURRENT_DATA_PATH = DEFAULT_DATA_PATH;
    }
}
