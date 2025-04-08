package org.example.data;

import java.util.Arrays;
import java.util.Date;

public class OrganizationBuilder {
    private final Long id = 0L; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name = "unknown"; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates = new CoordinatesBuilder().build(); //Поле не может быть null
    private java.util.Date creationDate = new Date(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float annualTurnover = 0; //Значение поля должно быть больше 0
    private Integer employeesCount = 0; //Поле не может быть null, Значение поля должно быть больше 0
    private OrganizationType type = OrganizationType.PUBLIC; //Поле не может быть null
    private Address officialAddress = new AddressBuilder().build(); //Поле может быть null

    public OrganizationBuilder setName(String name) {
        if(Validator.isNull(name)){
            return this;
        }
        this.name = name;
        return this;
    }

    public OrganizationBuilder setCoordinates(Coordinates coordinates) {
        if(Validator.isNull(coordinates)){
            return this;
        }
        this.coordinates = coordinates;
        return this;
    }

    public OrganizationBuilder setCreationDate(Date creationDate) {
        if(Validator.isNull(creationDate)){
            return this;
        }
        this.creationDate = creationDate;
        return this;
    }

    public OrganizationBuilder setAnnualTurnover(float annualTurnover) {
        if(annualTurnover<=0){
            return this;
        }
        this.annualTurnover = annualTurnover;
        return this;
    }

    public OrganizationBuilder setEmployeesCount(Integer employeesCount) {
        if(Validator.isNull(employeesCount)||employeesCount<=0){
            return this;
        }
        this.employeesCount = employeesCount;
        return this;
    }

    public OrganizationBuilder setType(OrganizationType type) {
        if(!(Arrays.stream(OrganizationType.values()).toList().contains(type))){
            return this;
        }
        this.type = type;
        return this;
    }

    public OrganizationBuilder setOfficialAddress(Address officialAddress) {
        if(Validator.isNull(officialAddress)){
            return this;
        }
        this.officialAddress = officialAddress;
        return this;
    }

    public Organization build(){
        return new Organization(IdManager.generateId(), name, coordinates, creationDate, annualTurnover,employeesCount,type,officialAddress);
    }
}
