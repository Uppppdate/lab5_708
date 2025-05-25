package org.example.data;

import org.example.files.DataErrorException;

/**
 * Билдер для класса Location
 *
 * @see Location
 */
public class LocationBuilder {
    private double x;
    /**
     * Значение переменной x по умолчанию
     */
    public static final double X_DEFAULT = 0d;
    private double y;
    /**
     * Значение переменной y по умолчанию
     */
    public static final double Y_DEFAULT = 0d;
    private Long z;
    /**
     * Значение переменной z по умолчанию
     */
    public static final Long Z_DEFAULT = 0L;

    public LocationBuilder() {
        setValuesAsDefault();
    }

    /**
     * Устанавливает значения по умолчанию
     */
    private void setValuesAsDefault() {
        x = X_DEFAULT;
        y = Y_DEFAULT;
        z = Z_DEFAULT;
    }

    /**
     * Устанавливает значение переменной x, при неправильном вводе устанавливает значение по умолчанию
     *
     * @param x строка с параметром для записи
     * @return текущий объект LocationBuilder
     */
    public LocationBuilder setX(String x) {
        try {
            Validator.checkLocationX(x);
        } catch (DataErrorException e) {
            return this;
        }
        this.x = Double.parseDouble(x);
        return this;
    }

    /**
     * Устанавливает значение переменной y, при неправильном вводе устанавливает значение по умолчанию
     *
     * @param y строка с параметром для записи
     * @return текущий объект LocationBuilder
     */
    public LocationBuilder setY(String y) {
        try {
            Validator.checkLocationY(y);
        } catch (DataErrorException e) {
            return this;
        }
        this.y = Double.parseDouble(y);
        return this;
    }

    /**
     * Устанавливает значение переменной z, при неправильном вводе устанавливает значение по умолчанию
     *
     * @param z строка с параметром для записи
     * @return текущий объект LocationBuilder
     */
    public LocationBuilder setZ(String z) {
        try {
            Validator.checkLocationZ(z);
        } catch (DataErrorException e) {
            return this;
        }
        this.z = Long.parseLong(z);
        return this;
    }

    /**
     * @return Собранный объект Location или null, если все значения по умолчанию
     */
    public Location build() {
        if (x == X_DEFAULT && y == Y_DEFAULT && z.equals(Z_DEFAULT)) {
            return null;
        }
        Location location = new Location(x, y, z);
        setValuesAsDefault();
        return location;
    }
}
