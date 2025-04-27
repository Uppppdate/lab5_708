package org.example.commands;

import org.example.files.DataWriter;
import org.example.files.PathManager;

public class SaveCommand extends BaseCommand{
    public SaveCommand() {
        super("save", "сохранить коллекцию в файл", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        DataWriter.toSave();
        System.out.println("Коллекция сохранена в файл: \n" + PathManager.CURRENT_DATA_PATH.toFile().getPath());
        return null;
    }
}
