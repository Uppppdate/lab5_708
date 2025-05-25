package org.example.data;

import org.example.files.DataErrorException;

/**
 * Билдер для класса координат
 *
 * @see Coordinates
 */
public class CoordinatesBuilder {
    private long x;
    /**
     * Значение переменной x по умолчанию
     */
    public static final long X_DEFAULT = 0;
    private double y;
    /**
     * Значение переменной y по умолчанию
     */
    public static final long Y_DEFAULT = 0;

    public CoordinatesBuilder() {
        setValuesAsDefault();
    }

    /**
     * Устанавливает значение по умолчанию
     */
    private void setValuesAsDefault() {
        x = X_DEFAULT;
        y = Y_DEFAULT;
    }

    /**
     * Устанавливает значение x, при неправильном вводе устанавливает значение по умолчанию
     *
     * @param x строка с параметром для записи
     * @return текущий объект CoordinatesBuilder
     */
    public CoordinatesBuilder setX(String x) {
        try {
            Validator.checkCoordinatesX(x);
        } catch (DataErrorException e) {
            return this;
        }
        this.x = Long.parseLong(x);
        return this;
    }

    /**
     * Устанавливает значение y, при неправильном вводе устанавливает значение по умолчанию
     *
     * @param y строка с параметром для записи
     * @return текущий объект CoordinatesBuilder
     */
    public CoordinatesBuilder setY(String y) {
        try {
            Validator.checkCoordinatesY(y);
        } catch (DataErrorException e) {
            return this;
        }
        this.y = Double.parseDouble(y);
        return this;
    }

    /**
     * @return собранный объект класса Coordinates
     */
    public Coordinates build() {
        Coordinates coordinates = new Coordinates(x, y);
        setValuesAsDefault();
        return coordinates;
    }
}
