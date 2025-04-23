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
        StringBuilder sb = new StringBuilder();
        sb.append("Улица: ");
        if (street==null || street.equals(AddressBuilder.STREET_DEFAULT)){
            sb.append("Неизвестна");
        } else {sb.append(street);}
        sb.append(", Индекс: ");
        if(zipCode==null || zipCode.equals(AddressBuilder.ZIP_CODE_DEFAULT)){
            sb.append("Неизвестен");
        } else {sb.append(zipCode);}
        sb.append(", Город: ");
        if (town == null){
            sb.append("Неизвестен");
        } else {
            sb.append(town);
        }
        return sb.toString();
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
