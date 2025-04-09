package org.example.data;

public class AddressBuilder {
    private String street; //Длина строки не должна быть больше 85, Поле может быть null
    private String zipCode; //Поле не может быть null
    private Location town; //Поле может быть null

    public AddressBuilder() {
        setValuesAsDefault();
    }
    private void setValuesAsDefault(){
        street = " ";
        zipCode = " ";
        town = new LocationBuilder().build();
    }
    public AddressBuilder setStreet(String street){
        if(Validator.checkStreet(street)){
            this.street = street;
            return this;
        }
        return this;
    }
    public AddressBuilder setZipCode(String zipCode){
        if(Validator.isNull(zipCode)){
            return this;
        }
        this.zipCode = zipCode;
        return this;
    }
    public AddressBuilder setLocation(Location town){
        if(Validator.isNull(town)){
            return this;
        }
        this.town = town;
        return this;
    }
    public Address build(){
        Address address = new Address(street, zipCode, town);
        setValuesAsDefault();
        return address;
    }
}
