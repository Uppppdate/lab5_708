package org.example.data;

public class Address {
    private String street; //Длина строки не должна быть больше 85, Поле может быть null
    private String zipCode; //Поле не может быть null
    private Location town; //Поле может быть null

    public Address(String street, String zipCode, Location town) {
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
    }
}
