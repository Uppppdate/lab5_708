package org.example.data;

import org.example.files.DataErrorException;

public class CoordinatesBuilder {
    private long x;
    public static final long X_DEFAULT = 0;
    private double y;
    public static final long Y_DEFAULT = 0;
    public CoordinatesBuilder() {
        setValuesAsDefault();
    }

    private void setValuesAsDefault() {
        x = X_DEFAULT;
        y = Y_DEFAULT;
    }

    public CoordinatesBuilder setX(String x) {
        try{
            Validator.checkCoordinatesX(x);
        } catch (DataErrorException e){
            return this;
        }
        this.x = Long.parseLong(x);
        return this;
    }

    public CoordinatesBuilder setY(String y) {
        try{
            Validator.checkCoordinatesY(y);
        } catch (DataErrorException e){
            return this;
        }
        this.y = Double.parseDouble(y);
        return this;
    }

    public Coordinates build() {
        Coordinates coordinates = new Coordinates(x, y);
        setValuesAsDefault();
        return coordinates;
    }
}
