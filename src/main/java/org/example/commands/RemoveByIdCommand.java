package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.data.Validator;
import org.example.files.DataErrorException;

public class RemoveByIdCommand extends BaseCommand {
    public RemoveByIdCommand() {
        super("remove_by_id", "удалить элемент из коллекции по его id", new String[]{"id"});
    }

    @Override
    public String execute(String[] args) throws CommandException {
        try {
            Validator.checkArgs(args);
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        }
        try {
            Validator.checkLong(args[1]);
        } catch (DataErrorException e) {
            throw new CommandException("Неправильно указан ID");
        }
        try {
            CollectionManager.removeOrganization(Long.valueOf(args[1]));
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        }
        System.out.println("Удалена организация с ID: " + args[1]);
        return null;
    }
}
