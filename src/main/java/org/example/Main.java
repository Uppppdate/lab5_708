package org.example;

import org.example.collection.CollectionManager;
import org.example.data.*;
import org.example.files.DataParser;
import org.example.files.DataWriter;
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
        }
        OrganizationBuilder or = new OrganizationBuilder();
        Organization org = or.generateId()
                .setCoordinatesX("451")
                .setCoordinatesY("3456789")
                .setCreationDate()
                .setType(OrganizationType.OPEN_JOINT_STOCK_COMPANY.name()).build();
        CollectionManager.getOrgSet().add(org);
        DataWriter dw = new DataWriter();
        dw.toSave();
        for(Organization organization : CollectionManager.getOrgSet()){
            System.out.println(organization);
        }
    }
}