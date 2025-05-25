package org.example.commands;

import static org.example.Main.clm;
import org.example.data.IdManager;
import org.example.files.DataErrorException;

/**
 * Команда add
 */
public class AddCommand extends BaseCommand {

    /**
     * Конструктор
     */
    public AddCommand() {
        super("add", "добавить новый элемент в коллекцию", new String[]{});
    }

    /**
     * Метод, выполняющий команду add
     *
     * @param args массив с данными для создания организации без ID
     * @throws CommandException если есть ошибка в данных,
     *                          но всё равно добавляет объект со значениями по умолчанию для
     *                          неправильных данных
     */
    @Override
    public void execute(String[] args) throws CommandException {
        try {
            //создаю массив для записи данных
            String[] data = new String[13];
            //генерирую свободный ID
            data[0] = String.valueOf(IdManager.generateIdWithoutAdding());
            //Копирую элементы из args в data, начиная с индекса 1, чтобы добавить ID в начало
            System.arraycopy(args, 0, data, 1, args.length);
            //Добавляю организацию
            clm.addOrganizationFromData(data);
        } catch (DataErrorException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
