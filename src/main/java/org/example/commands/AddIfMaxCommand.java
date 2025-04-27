package org.example.commands;

import org.example.data.IdManager;
import org.example.data.OrganizationBuilder;
import org.example.files.DataErrorException;
import org.example.managers.CollectionManager;

public class AddIfMaxCommand extends BaseCommand{
    public AddIfMaxCommand() {
        super("add_if_max", "добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции", new String[]{});
    }

    @Override
    public String execute(String[] args) throws CommandException {
        try {
            String[] data = new String[13];
            //генерирую свободный ID
            data[0] = String.valueOf(IdManager.generateIdWithoutAdding());
            // Копируем элементы из args в data, начиная с индекса 1, чтобы добавить ID в начало
            System.arraycopy(args, 0, data, 1, args.length);
            // Проверяю больше ли переданная организация, чем наибольшая в коллекции
            if(OrganizationBuilder.buildWithData(data).compareTo(CollectionManager.getMax()) > 0) {
                CollectionManager.addOrganizationFromData(data);
            }
            else System.out.println("Переданная организация не больше, чем наибольшая в коллекции");
        } catch (DataErrorException e){
            throw new CommandException(e.getMessage());
        }
        return null;
    }
}
