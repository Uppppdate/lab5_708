package org.example.collection;

import org.example.data.*;
import org.example.files.DataErrorException;
import org.example.files.DataParser;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashSet;

public class CollectionManager {
    private static LinkedHashSet<Organization> orgSet;

    public CollectionManager() {
        orgSet = new LinkedHashSet<>();
    }

    public static LinkedHashSet<Organization> getOrgSet() {
        return orgSet;
    }

    public static void setOrgSet(LinkedHashSet<Organization> orgSet) {
        CollectionManager.orgSet = orgSet;
    }

    public static void addOrganizationFromData(String[] data) throws DataErrorException{
        try {
            Validator.checkData(data);
        } finally {
        String[] fullsize_data = Arrays.copyOf(data, 13);
        Arrays.fill(fullsize_data, data.length, fullsize_data.length, "0");
        OrganizationBuilder orb = new OrganizationBuilder();
        Organization org = orb
                .setId(fullsize_data[0])
                .setName(fullsize_data[1])
                .setCoordinatesX(fullsize_data[2])
                .setCoordinatesY(fullsize_data[3])
                .setCreationDate(fullsize_data[4])
                .setAnnualTurnover(fullsize_data[5])
                .setEmployeesCount(fullsize_data[6])
                .setType(fullsize_data[7])
                .setAddressStreet(fullsize_data[8])
                .setAddressZipCode(fullsize_data[9])
                .setLocationX(fullsize_data[10])
                .setLocationY(fullsize_data[11])
                .setLocationZ(fullsize_data[12])
                .build();
        orgSet.add(org);
        IdManager.addId(org.getId());
        }
    }
}
