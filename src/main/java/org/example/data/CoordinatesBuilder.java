package org.example.data;

import org.example.files.DataErrorException;

public class CoordinatesBuilder {
    private long x;
    private double y;

    public CoordinatesBuilder() {
        setValuesAsDefault();
    }

    private void setValuesAsDefault() {
        x = 0;
        y = 0;
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
