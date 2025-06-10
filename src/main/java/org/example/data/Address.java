package org.example.data;

/**
 * Класс Address
 */
public class Address {
    private Long id;
    private String street;
    private String zipCode;
    /**
     * Переменная классa Location
     */
    private Location town;

    public Address() {
    }

    public Address(String street, String zipCode, Location town) {
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Location getTown() {
        return town;
    }

    public void setTown(Location town) {
        this.town = town;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Улица: ");
        if (street == null || street.equals(AddressBuilder.STREET_DEFAULT)) {
            sb.append("Неизвестна");
        } else {
            sb.append(street);
        }
        sb.append(", Индекс: ");
        if (zipCode == null || zipCode.equals(AddressBuilder.ZIP_CODE_DEFAULT)) {
            sb.append("Неизвестен");
        } else {
            sb.append(zipCode);
        }
        sb.append(", Город: ");
        if (town == null) {
            sb.append("Неизвестен");
        } else {
            sb.append(town);
        }
        return sb.toString();
    }

    /**
     * @return строка для записи в csv файл
     */
    public String toCsv() {
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb
                .append(street).append(del).append(zipCode).append(del);
        if (town == null) {
            sb.append(Location.toCsvDefault());
        } else {
            sb.append(town.toCsv());
        }
        return sb.toString();
    }

    /**
     * @return строка записи в csv по умолчанию
     */
    public static String toCsvDefault() {
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb
                .append(AddressBuilder.STREET_DEFAULT).append(del)
                .append(AddressBuilder.ZIP_CODE_DEFAULT).append(del)
                .append(Location.toCsvDefault());
        return sb.toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
