package org.example;

import org.example.data.*;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) {
        AddressBuilder arb = new AddressBuilder();
        CoordinatesBuilder crb = new CoordinatesBuilder();
        LocationBuilder lcb = new LocationBuilder();
        OrganizationBuilder orb = new OrganizationBuilder();

        LinkedHashSet<Organization> orgSet = new LinkedHashSet<>();
        Organization org1 = orb
                .generateId()
                .setName("ITMO")
                .setAnnualTurnover(3205)
                .setCoordinates(crb
                        .setX(34521)
                        .setY(2234)
                        .build())
                .setEmployeesCount(3241)
                .setOfficialAddress(arb
                        .setStreet("KRONVA")
                        .setZipCode("186500")
                        .setLocation(lcb
                                .setX(32)
                                .setY(215)
                                .setZ(342L)
                                .build())
                        .build())
                .setType(OrganizationType.COMMERCIAL)
                .build();

        orgSet.add(org1);

        Organization org2 = orb
                .setId(34253647L)
                .setName("ITMOoiuijvkt jl")
                .setAnnualTurnover(-41256982)
                .setCoordinates(crb
                        .build())
                .setEmployeesCount(32413444)
                .setCreationDate()
                .setOfficialAddress(arb
                        .setStreet("KRONVAkeskrgklrharentr;mrelsnrklmjlsnacermlsvnvkeamlrnsaemlkskvlcarmetskmcaelsvkmvslykdvslkvrdm")
                        .setZipCode("186500")
                        .setLocation(lcb
                                .setX(32)
                                .setZ(null)
                                .build())
                        .build())
                .setType(OrganizationType.GOVERNMENT)
                .build();

        orgSet.add(org2);

        for (Organization org : orgSet) {
            System.out.println(org.toString());
        }

    }
}