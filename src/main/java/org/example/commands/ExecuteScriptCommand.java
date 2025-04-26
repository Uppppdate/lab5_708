package org.example.commands;

import org.example.console.ConsoleManager;
import org.example.data.Validator;
import org.example.files.DataErrorException;
import org.example.files.FileErrorException;
import org.example.files.PathManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.Stack;

public class ExecuteScriptCommand extends BaseCommand{

    private static Stack<File> scriptsStack = new Stack<>();
    public ExecuteScriptCommand() {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.", new String[]{"file_name"});
    }

    @Override
    public String execute(String[] args) throws CommandException {
        ConsoleManager csm = new ConsoleManager();
        File script = null;
        try{
            script = PathManager.getFileFromPath(args[1]);
            if(scriptsStack.contains(script)){
                throw new RecursionException(script.getName());
            };
        } catch (DataErrorException e){
            throw new CommandException("Передан неправильный путь: " + e.getMessage());
        } catch (FileErrorException | RecursionException e){
            throw new CommandException(e.getMessage());
        }
        InputStream is = null;
        try {
            is = new FileInputStream(script);
        } catch (FileNotFoundException e) {
            throw new CommandException(e.getMessage());
        }
        scriptsStack.push(script);
        csm.toStart(args, is);
        scriptsStack.pop();
        return null;
    }



}
