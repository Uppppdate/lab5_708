package org.example;

import org.example.managers.CollectionManager;
import org.example.commands.Invoker;
import org.example.managers.ConsoleManager;
import org.example.data.*;

public class Main {
    public static void main(String[] args) {
        //initialize collection
        CollectionManager clm = new CollectionManager();
        ConsoleManager csm = new ConsoleManager();
        Invoker inv = new Invoker();
        csm.toDetermineDataPath(args, System.in);
        csm.toStart(args, System.in);
        for (Organization org : CollectionManager.getOrgSet()) {
            System.out.println(org);
        }
    }
}