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

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Location getTown() {
        return town;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", town=" + town +
                '}';
    }

    public String toCsv(){
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb
                .append(street).append(del).append(zipCode).append(del);
        if(town==null){
            sb.append(Location.toCsvDefault());
        }else {
            sb.append(town.toCsv());
        }
        return sb.toString();
    }

    public static String toCsvDefault(){
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb
                .append(AddressBuilder.STREET_DEFAULT).append(del)
                .append(AddressBuilder.ZIP_CODE_DEFAULT).append(del)
                .append(Location.toCsvDefault());
        return sb.toString();
    }
}
