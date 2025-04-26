package org.example.collection;

import org.example.data.*;
import org.example.files.DataErrorException;
import org.example.files.DataParser;

import java.util.*;

public class CollectionManager {
    private static LinkedHashSet<Organization> orgSet;

    private static Date initializationDate;

    public CollectionManager() {
        initializationDate = new Date();
        orgSet = new LinkedHashSet<>();
    }

    public static LinkedHashSet<Organization> getOrgSet() {
        return orgSet;
    }

    public static Date getInitializationDate() {
        return initializationDate;
    }

    public static void clearCollection() {
        orgSet.clear();
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

    public static void removeOrganization(Long id) throws DataErrorException {
        if(!IdManager.checkId(id)){
            throw new DataErrorException("Указанного ID не существует");
        }
        IdManager.removeId(id);
        Optional<Organization> organization = orgSet.stream().filter(org -> org.getId().equals(id)).findFirst();
        organization.ifPresent(value -> orgSet.remove(value));
    }

    public static void removeOrganization(Organization organization) throws DataErrorException {
        if(!orgSet.contains(organization)){
            throw new DataErrorException("Указанная организация не содержится в коллекции");
        }
        Long id = organization.getId();
        IdManager.removeId(id);
        orgSet.remove(organization);
    }

    public static Organization getOrgById(Long id) throws DataErrorException{
        if(!IdManager.checkId(id)){
            throw new DataErrorException("Указанного ID не существует");
        }
        return orgSet.stream().filter(org -> org.getId().equals(id)).findFirst().get();
    }
}
