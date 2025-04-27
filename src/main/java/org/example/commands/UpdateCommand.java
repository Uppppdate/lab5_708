package org.example.commands;

import org.example.files.DataErrorException;
import org.example.managers.CollectionManager;

public class UpdateCommand extends BaseCommand{
    public UpdateCommand() {
        super("update", "обновить значение элемента коллекции, id которого равен заданному", new String[]{"id"});
    }

    @Override
    public String execute(String[] args) throws CommandException {
        try {
            CollectionManager.removeOrganization(Long.valueOf(args[0]));
            CollectionManager.addOrganizationFromData(args);
        } catch (DataErrorException e){
            throw new CommandException(e.getMessage());
        }
        return null;
    }
}
