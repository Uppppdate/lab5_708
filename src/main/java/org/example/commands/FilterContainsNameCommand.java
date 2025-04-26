package org.example.commands;

import org.example.collection.CollectionManager;
import org.example.data.Organization;
import org.example.data.Validator;
import org.example.files.DataErrorException;

import java.util.LinkedHashSet;
import java.util.List;

public class FilterContainsNameCommand extends BaseCommand{
    public FilterContainsNameCommand() {
        super("filter_contains_name", "вывести элементы, значение поля name которых содержит заданную подстроку", new String[]{"name"});
    }

    @Override
    public String execute(String[] args) throws CommandException {
        try {
            Validator.checkArgs(args);
        } catch (DataErrorException e){
            throw new CommandException(e.getMessage());
        }
        List<Organization> set = CollectionManager.getOrgSet().stream().filter(organization -> organization.getName().contains(args[1])).toList();
        if (set.isEmpty()) {
            System.out.println("Не было найдено объектов с такой подстрокой в имени");
        } else {
            for (Organization org : CollectionManager.getOrgSet()) {
                System.out.println(org.toString());
            }
        }
        return null;
    }
}
