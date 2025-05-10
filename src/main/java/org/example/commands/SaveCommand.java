package org.example.commands;

import org.example.files.DataWriter;
import org.example.files.PathManager;

/**
 * Команда save сохраняет коллекцию в файл
 */
public class SaveCommand extends BaseCommand {
    public SaveCommand() {
        super("save", "сохранить коллекцию в файл", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        //Сохраняю коллекцию
        DataWriter.toSave();
        //Вывожу сообщение
        System.out.println("Коллекция сохранена в файл: \n" + PathManager.CURRENT_DATA_PATH.toFile().getPath());
        return null;
    }
}
