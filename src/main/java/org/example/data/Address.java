package org.example.data;

public class Address {
    private String street;
    private String zipCode;
    private Location town;

    public Address(String street, String zipCode, Location town) {
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", town=" + town +
                '}';
    }
}
