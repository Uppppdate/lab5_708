package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.data.IdManager;
import org.example.files.DataErrorException;

public class AddCommand extends BaseCommand{
    public AddCommand() {
        super("add", "добавить новый элемент в коллекцию", new String[]{});
    }

    @Override
    public String execute(String[] args) throws CommandException {
        try {
            String[] data = new String[13];
            //генерирую свободный ID
            data[0] = String.valueOf(IdManager.generateIdWithoutAdding());
            // Копируем элементы из args в data, начиная с индекса 1, чтобы добавить ID в начало
            System.arraycopy(args, 0, data, 1, args.length);
            CollectionManager.addOrganizationFromData(data);
        } catch (DataErrorException e){
            throw new CommandException(e.getMessage());
        }
        return null;
    }
}
