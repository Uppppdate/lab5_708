package org.example.data;

import org.example.files.DataErrorException;

/**
 * Билдер для класса Address
 * Собирает объект и при неподходящих данных присваивает значения по умолчанию
 */
public class AddressBuilder {
    private String street;
    /**
     * Значение переменной street по умолчанию
     */
    public static final String STREET_DEFAULT = "0";
    private String zipCode;
    /**
     * Значение переменной zipCode по умолчанию
     */
    public static final String ZIP_CODE_DEFAULT = "0";
    /**
     * Переменная типа Location
     */
    private Location town;
    /**
     * Билдер для переменной town
     */
    private LocationBuilder lcb;

    public AddressBuilder() {
        setValuesAsDefault();
    }

    /**
     * Метод устанавливает значение переменных по умолчанию
     */
    private void setValuesAsDefault() {
        lcb = new LocationBuilder();
        street = STREET_DEFAULT;
        zipCode = ZIP_CODE_DEFAULT;
        town = lcb.build();
    }

    /**
     * Устанавливает значение переменной street, при неправильном вводе устанавливает значения по умолчанию
     *
     * @param street
     * @return
     */
    public AddressBuilder setStreet(String street) {
        try {
            Validator.checkStreet(street);
        } catch (DataErrorException e) {
            return this;
        }
        this.street = street;
        return this;
    }

    /**
     * Устанавливает значение переменной zipCode, при неправильном вводе устанавливает значения по умолчанию
     *
     * @param zipCode
     * @return
     */
    public AddressBuilder setZipCode(String zipCode) {
        try {
            Validator.checkZipCode(zipCode);
        } catch (DataErrorException e) {
            return this;
        }
        this.zipCode = zipCode;
        return this;
    }

    /**
     * @param x
     * @return
     * @see LocationBuilder#setX(String)
     */
    public AddressBuilder setLocationX(String x) {
        lcb.setX(x);
        return this;
    }

    /**
     * @param y
     * @return
     * @see LocationBuilder#setY(String)
     */
    public AddressBuilder setLocationY(String y) {
        lcb.setY(y);
        return this;
    }

    /**
     * @param z
     * @return
     * @see LocationBuilder#setZ(String)
     */
    public AddressBuilder setLocationZ(String z) {
        lcb.setZ(z);
        return this;
    }

    /**
     * Собирает объект Address
     *
     * @return Собранный объект типа Address или null, если все значения по умолчанию
     */
    public Address build() {
        //Собираем локацию через LocationBuilder
        Location location = lcb.build();
        //Проверяем на дефолтность значений и возвращаем null, если ничего не известно
        if (street.equals(STREET_DEFAULT) && zipCode.equals(ZIP_CODE_DEFAULT) && location == null) {
            return null;
        }
        Address address = new Address(street, zipCode, location);
        //Обнуляем значения
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
