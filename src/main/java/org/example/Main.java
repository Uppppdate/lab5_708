package org.example;

import org.example.collection.CollectionManager;
import org.example.data.*;
import org.example.files.DataParser;
import org.example.files.ParsingException;

public class Main {
    public static void main(String[] args) {
        //initialize collection
        CollectionManager clm = new CollectionManager();
        DataParser dp = new DataParser();
        try {
            dp.toParse("321");
        } catch (ParsingException e) {
            System.out.println(e.getMessage());
            for (Organization org : CollectionManager.getOrgSet()) {
                System.out.println(org.toString());
            }
        }

    }
}