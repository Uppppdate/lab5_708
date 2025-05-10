package org.example.files;

/**
 * Ошибка при работе с файлом
 */
public class FileErrorException extends Exception {
    public FileErrorException(String path) {
        super("Ошибка с файлом на пути: " + path);
    }
}
