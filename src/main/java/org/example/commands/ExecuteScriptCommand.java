package org.example.commands;

import org.example.files.DataErrorException;
import org.example.files.FileErrorException;
import org.example.files.PathManager;
import org.example.managers.ScriptManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


/**
 * Команда execute_script
 */
public class ExecuteScriptCommand extends BaseCommand {

    /**
     * Конструктор
     */
    public ExecuteScriptCommand() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.", new String[]{"file_name"});
    }

    /**
     * Метод, реализующий команду execute_script
     *
     * @param args содержит в себе имя команды и аргумент, который является путём к файлу скрипта
     * @throws CommandException любая ошибка, возникшая в ходе работы команды будет обёрнута в CommandException и проброшена далее
     */
    @Override
    public void execute(String[] args) throws CommandException {
        //Создаю менеджер скрипта для работы с командами из скрипта
        ScriptManager scriptManager = new ScriptManager();
        //Объявляю файл
        File script;
        try {
            //Получаю файл из пути
            script = PathManager.getFileFromPath(args[1]);
            //Проверяю на рекурсию (наличие имени файла в стэке используемых файлов)
            if (ScriptManager.getScriptsStack().contains(script)) {
                //Пробрасываю ошибку рекурсии
                throw new RecursionException(script.getName());
            }
        } catch (DataErrorException e) {
            //Перепробрасываю ошибку
            throw new CommandException("Передан неправильный путь: " + e.getMessage());
        } catch (FileErrorException | RecursionException e) {
            //Перепробрасываю ошибку
            throw new CommandException(e.getMessage());
        }
        //Объявляю fileInputStream для работы с файлом в потоке
        FileInputStream is;
        try {
            is = new FileInputStream(script);
        } catch (FileNotFoundException e) {
            throw new CommandException(e.getMessage());
        }
        //Добавляю имя файла в стэк используемых файлов(скриптов)
        ScriptManager.getScriptsStack().push(script);
        //Запускаю чтение и выполнение команд из скрипта
        scriptManager.toStart(is);
        //Удаляю имя файла из стэка
        ScriptManager.getScriptsStack().pop();
    }
}
