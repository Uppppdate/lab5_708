package org.example.data;

import org.example.files.DataErrorException;

public class AddressBuilder {
    private String street;
    public static final String STREET_DEFAULT = " ";
    private String zipCode;
    public static final String ZIP_CODE_DEFAULT = " ";
    private Location town;
    private LocationBuilder lcb;

    public AddressBuilder() {
        setValuesAsDefault();
    }

    private void setValuesAsDefault() {
        lcb = new LocationBuilder();
        street = STREET_DEFAULT;
        zipCode = ZIP_CODE_DEFAULT;
        town = lcb.build();
    }

    public AddressBuilder setStreet(String street) {
        try {
            Validator.checkStreet(street);
        } catch (DataErrorException e) {
            return this;
        }
        this.street = street;
        return this;
    }

    public AddressBuilder setZipCode(String zipCode) {
        try {
            Validator.checkZipCode(zipCode);
        } catch (DataErrorException e) {
            return this;
        }
        this.zipCode = zipCode;
        return this;
    }

    public AddressBuilder setLocationX(String x) {
        lcb.setX(x);
        return this;
    }

    public AddressBuilder setLocationY(String y) {
        lcb.setY(y);
        return this;
    }

    public AddressBuilder setLocationZ(String z) {
        lcb.setZ(z);
        return this;
    }

    public Address build() {
        Location location = lcb.build();
        if (street.equals(STREET_DEFAULT) && zipCode.equals(ZIP_CODE_DEFAULT) && lcb==null) {
            return null;
        }
        Address address = new Address(street, zipCode, location);
        setValuesAsDefault();
        return address;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Location getTown() {
        return town;
    }
}
