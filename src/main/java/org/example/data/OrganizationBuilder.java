package org.example.data;

import org.example.files.DataErrorException;
import org.example.files.DataParser;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

/**
 * Билдер для класса Organization
 *
 * @see Organization
 */
public class OrganizationBuilder {
    private Long id;
    /**
     * Значение id по умолчанию
     */
    public static final Long ID_DEFAULT = 0L;
    private String name;
    /**
     * Значение name по умолчанию
     */
    public static final String NAME_DEFAULT = "unknown";
    private Coordinates coordinates;
    private CoordinatesBuilder crb;
    private java.util.Date creationDate;
    /**
     * Значение creationDate по умолчанию
     */
    public static final Date CREATION_DATE_DEFAULT = new Date(0);
    private float annualTurnover;
    /**
     * Значение annualTurnover по умолчанию
     */
    public static final float ANNUAL_TURNOVER_DEFAULT = 1L;
    private Integer employeesCount;
    /**
     * Значение employeesCount по умолчанию
     */
    public static final Integer EMPLOYEES_COUNT_DEFAULT = 1;
    private OrganizationType type;
    /**
     * Значение type по умолчанию
     */
    public static final OrganizationType ORGANIZATION_TYPE_DEFAULT = OrganizationType.PUBLIC;
    private Address officialAddress;

    private AddressBuilder arb;

    public OrganizationBuilder() {
        setValuesAsDefault();
    }

    /**
     * Устанавливает значения по умолчанию
     */
    private void setValuesAsDefault() {
        crb = new CoordinatesBuilder();
        arb = new AddressBuilder();
        id = ID_DEFAULT;
        name = NAME_DEFAULT;
        coordinates = crb.build();
        creationDate = CREATION_DATE_DEFAULT;
        annualTurnover = ANNUAL_TURNOVER_DEFAULT;
        employeesCount = EMPLOYEES_COUNT_DEFAULT;
        type = ORGANIZATION_TYPE_DEFAULT;
        officialAddress = arb.build();
    }

    /**
     * Устанавливает id. В случае неправильного ввода устанавливает значения по умолчанию.
     *
     * @param id
     * @return
     */
    public OrganizationBuilder setId(String id) {
        try {
            Validator.checkId(id);
        } catch (DataErrorException e) {
            return this;
        }
        this.id = Long.parseLong(id);
        return this;
    }

    /**
     * Устанавливает name. В случае неправильного ввода устанавливает значения по умолчанию.
     *
     * @param name
     * @return
     */
    public OrganizationBuilder setName(String name) {
        try {
            Validator.checkName(name);
        } catch (DataErrorException e) {
            return this;
        }
        this.name = name;
        return this;
    }

    /**
     * @param x
     * @return
     * @see CoordinatesBuilder#setX(String)
     */
    public OrganizationBuilder setCoordinatesX(String x) {
        crb.setX(x);
        return this;
    }

    /**
     * @param y
     * @return
     * @see CoordinatesBuilder#setY(String)
     */
    public OrganizationBuilder setCoordinatesY(String y) {
        crb.setY(y);
        return this;
    }

    /**
     * Устанавливает creationDate. В случае неправильного ввода устанавливает значения по умолчанию.
     *
     * @param creationDate
     * @return
     */
    public OrganizationBuilder setCreationDate(String creationDate) {
        if (creationDate.equals("current")) {
            this.creationDate = new Date();
            return this;
        }
        try {
            Validator.checkDate(creationDate);
        } catch (DataErrorException e) {
            return this;
        }
        try {
            this.creationDate = DataParser.formatter.parse(creationDate);
        } catch (ParseException e) {
            return this;
        }
        return this;
    }

    /**
     * Устанавливает annualTurnover. В случае неправильного ввода устанавливает значения по умолчанию.
     *
     * @param annualTurnover
     * @return
     */
    public OrganizationBuilder setAnnualTurnover(String annualTurnover) {
        try {
            Validator.checkAnnualTurnover(annualTurnover);
        } catch (DataErrorException e) {
            return this;
        }
        this.annualTurnover = Float.parseFloat(annualTurnover);
        return this;
    }

    /**
     * Устанавливает employeesCount. В случае неправильного ввода устанавливает значения по умолчанию.
     *
     * @param employeesCount
     * @return
     */
    public OrganizationBuilder setEmployeesCount(String employeesCount) {
        try {
            Validator.checkEmployeesCount(employeesCount);
        } catch (DataErrorException e) {
            return this;
        }
        this.employeesCount = Integer.parseInt(employeesCount);
        return this;
    }


    /**
     * @param street
     * @return
     * @see AddressBuilder#setStreet(String)
     */
    public OrganizationBuilder setAddressStreet(String street) {
        arb.setStreet(street);
        return this;
    }

    /**
     * @param zipCode
     * @return
     * @see AddressBuilder#setZipCode(String)
     */
    public OrganizationBuilder setAddressZipCode(String zipCode) {
        arb.setZipCode(zipCode);
        return this;
    }


    /**
     * @param x
     * @return
     * @see AddressBuilder#setLocationX(String)
     */
    public OrganizationBuilder setLocationX(String x) {
        arb.setLocationX(x);
        return this;
    }


    /**
     * @param y
     * @return
     * @see AddressBuilder#setLocationY(String)
     */
    public OrganizationBuilder setLocationY(String y) {
        arb.setLocationY(y);
        return this;
    }

    /**
     * @param z
     * @return
     * @see AddressBuilder#setLocationZ(String)
     */
    public OrganizationBuilder setLocationZ(String z) {
        arb.setLocationZ(z);
        return this;
    }

    /**
     * Устанавливает type. В случае неправильного ввода устанавливает значения по умолчанию.
     *
     * @param type
     * @return
     */
    public OrganizationBuilder setType(String type) {
        try {
            Validator.checkOrganizationType(type);
        } catch (DataErrorException e) {
            return this;
        }
        this.type = OrganizationType.valueOf(type.toUpperCase());
        return this;
    }


    /**
     * @return собранный объект Organization
     */
    public Organization build() {
        coordinates = crb.build();
        officialAddress = arb.build();
        Organization org = new Organization(id, name, coordinates, creationDate, annualTurnover, employeesCount, type, officialAddress);
        setValuesAsDefault();
        return org;
    }

    /**
     * Собирает объект Organization из не обязательно полных данных об объекте
     *
     * @param data данные для создания организации
     * @return собранный объект Organization
     */
    public static Organization buildWithData(String[] data) {
        String[] fullsize_data = Arrays.copyOf(data, 13);
        Arrays.fill(fullsize_data, data.length, fullsize_data.length, "0");
        OrganizationBuilder orb = new OrganizationBuilder();
        Organization org = orb
                .setId(fullsize_data[0])
                .setName(fullsize_data[1])
                .setCoordinatesX(fullsize_data[2])
                .setCoordinatesY(fullsize_data[3])
                .setCreationDate(fullsize_data[4])
                .setAnnualTurnover(fullsize_data[5])
                .setEmployeesCount(fullsize_data[6])
                .setType(fullsize_data[7])
                .setAddressStreet(fullsize_data[8])
                .setAddressZipCode(fullsize_data[9])
                .setLocationX(fullsize_data[10])
                .setLocationY(fullsize_data[11])
                .setLocationZ(fullsize_data[12])
                .build();
        return org;
    }
}
