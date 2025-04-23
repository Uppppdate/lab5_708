package org.example.commands;

import org.example.files.DataWriter;

public class SaveCommand extends BaseCommand{
    public SaveCommand() {
        super("save", "сохранить коллекцию в файл", new String[]{});
    }

    @Override
    public String execute(String[] args) {
        DataWriter.toSave();
        return null;
    }
}
