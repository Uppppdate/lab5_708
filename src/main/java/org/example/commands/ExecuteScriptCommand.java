package org.example.commands;

import org.example.managers.ConsoleManager;
import org.example.files.DataErrorException;
import org.example.files.FileErrorException;
import org.example.files.PathManager;
import org.example.managers.ScriptManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Stack;

public class ExecuteScriptCommand extends BaseCommand{


    public ExecuteScriptCommand() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.", new String[]{"file_name"});
    }

    @Override
    public String execute(String[] args) throws CommandException {
        ScriptManager scriptManager = new ScriptManager();
        File script = null;
        try{
            script = PathManager.getFileFromPath(args[1]);
            if(ScriptManager.getScriptsStack().contains(script)){
                throw new RecursionException(script.getName());
            };
        } catch (DataErrorException e){
            throw new CommandException("Передан неправильный путь: " + e.getMessage());
        } catch (FileErrorException | RecursionException e){
            throw new CommandException(e.getMessage());
        }
        FileInputStream is = null;
        try {
            is = new FileInputStream(script);
        } catch (FileNotFoundException e) {
            throw new CommandException(e.getMessage());
        }
        ScriptManager.getScriptsStack().push(script);
        scriptManager.toStart(args, is);
        ScriptManager.getScriptsStack().pop();
        return null;
    }
}
