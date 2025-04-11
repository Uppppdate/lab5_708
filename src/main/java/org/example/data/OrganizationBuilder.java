package org.example.data;

import org.example.files.DataErrorException;
import org.example.files.DataParser;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

public class OrganizationBuilder {
    private Long id;
    public static final Long ID_DEFAULT = 0L;
    private String name;
    public static final String NAME_DEFAULT = "unknown";
    private Coordinates coordinates;
    private CoordinatesBuilder crb;
    private java.util.Date creationDate;
    public static final Date CREATION_DATE_DEFAULT = new Date(0);
    private float annualTurnover;
    public static final float ANNUAL_TURNOVER_DEFAULT = 1L;
    private Integer employeesCount;
    public static final Integer EMPLOYEES_COUNT_DEFAULT = 1;
    private OrganizationType type;
    public static final OrganizationType ORGANIZATION_TYPE_DEFAULT = OrganizationType.PUBLIC;
    private Address officialAddress;

    private AddressBuilder arb;

    public OrganizationBuilder() {
        setValuesAsDefault();
    }

    private void setValuesAsDefault() {
        crb = new CoordinatesBuilder();
        arb = new AddressBuilder();
        id = ID_DEFAULT;
        name = NAME_DEFAULT;
        coordinates = crb.build();
        creationDate = CREATION_DATE_DEFAULT;
        annualTurnover = ANNUAL_TURNOVER_DEFAULT;
        employeesCount = EMPLOYEES_COUNT_DEFAULT;
        type = ORGANIZATION_TYPE_DEFAULT;
        officialAddress = arb.build();
    }

    public OrganizationBuilder setId(String id) {
        try {
            Validator.checkId(id);
        } catch (DataErrorException e) {
            return this;
        }
        this.id = Long.parseLong(id);
        return this;
    }

    public OrganizationBuilder generateId() {
        id = IdManager.generateId();
        return this;
    }

    public OrganizationBuilder setName(String name) {
        try {
            Validator.checkName(name);
        } catch (DataErrorException e){
            return this;
        }
        this.name = name;
        return this;
    }

    public OrganizationBuilder setCoordinates(Coordinates coordinates) {
        try {
            Validator.isNull(coordinates);
        } catch (NullPointerException e){
            return this;
        }
        this.coordinates = coordinates;
        return this;
    }

    public OrganizationBuilder setCoordinatesX(String x) {
        crb.setX(x);
        return this;
    }

    public OrganizationBuilder setCoordinatesY(String y) {
        crb.setY(y);
        return this;
    }

    public OrganizationBuilder setCreationDate(String creationDate) {
        try {
            Validator.checkDate(creationDate);
        } catch (DataErrorException e){
            return this;
        }
        try {
            this.creationDate = DataParser.formatter.parse(creationDate);
        } catch (ParseException e) {
            return this;
        }
        return this;
    }

    public OrganizationBuilder setCreationDate() {
        this.creationDate = new Date();
        return this;
    }

    public OrganizationBuilder setAnnualTurnover(String annualTurnover) {
        try {
            Validator.checkAnnualTurnover(annualTurnover);
        } catch (DataErrorException e){
            return this;
        }
        this.annualTurnover = Float.parseFloat(annualTurnover);
        return this;
    }

    public OrganizationBuilder setEmployeesCount(String employeesCount) {
        try {
            Validator.checkEmployeesCount(employeesCount);
        } catch (DataErrorException e){
            return this;
        }
        this.employeesCount = Integer.parseInt(employeesCount);
        return this;
    }

    public OrganizationBuilder setOfficialAddress(Address officialAddress) {
        try {
            Validator.isNull(name);
        } catch (NullPointerException e){
            return this;
        }
        this.officialAddress = officialAddress;
        return this;
    }

    public OrganizationBuilder setAddressStreet(String street) {
        arb.setStreet(street);
        return this;
    }

    public OrganizationBuilder setAddressZipCode(String zipCode) {
        arb.setZipCode(zipCode);
        return this;
    }

    public OrganizationBuilder setLocationX(String x) {
        arb.setLocationX(x);
        return this;
    }

    public OrganizationBuilder setLocationY(String y) {
        arb.setLocationY(y);
        return this;
    }

    public OrganizationBuilder setLocationZ(String z) {
        arb.setLocationZ(z);
        return this;
    }

    public OrganizationBuilder setType(String type) {
        try {
            Validator.checkOrganizationType(name);
        } catch (DataErrorException e){
            return this;
        }
        this.type = OrganizationType.valueOf(type);
        return this;
    }

    public Organization build() {
        coordinates = crb.build();
        officialAddress = arb.build();
        Organization org = new Organization(id, name, coordinates, creationDate, annualTurnover, employeesCount, type, officialAddress);
        setValuesAsDefault();
        return org;
    }
}
