package org.example.data;

import java.util.Arrays;
import java.util.Date;

public class OrganizationBuilder {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float annualTurnover; //Значение поля должно быть больше 0
    private Integer employeesCount; //Поле не может быть null, Значение поля должно быть больше 0
    private OrganizationType type; //Поле не может быть null
    private Address officialAddress; //Поле может быть null

    public OrganizationBuilder() {
        setValuesAsDefault();
    }

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

    public OrganizationBuilder setCreationDate(){
        if(Validator.isNull(creationDate)){
            return this;
        }
        this.creationDate = new Date();
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

    public OrganizationBuilder setId(Long id){
        if(!Validator.checkId(id)){
            return this;
        }
        this.id = id;
        return this;
    }
    public OrganizationBuilder generateId(){
        id = IdManager.generateId();
        return this;
    }
    private void setValuesAsDefault(){
        id = 0L;
        name = "unknown";
        coordinates = new CoordinatesBuilder().build();
        creationDate = new Date(0);
        annualTurnover = 0;
        employeesCount = 0;
        type = OrganizationType.PUBLIC;
        officialAddress = new AddressBuilder().build();
    }


    public Organization build(){
        Organization org =  new Organization(id, name, coordinates, creationDate, annualTurnover,employeesCount,type,officialAddress);
        setValuesAsDefault();
        return org;
    }
}
