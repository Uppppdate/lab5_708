package org.example.data;

import org.example.files.DataErrorException;

public class LocationBuilder {
    private double x;
    public static final double X_DEFAULT = 0d;
    private double y;
    public static final double Y_DEFAULT = 0d;
    private Long z;
    public static final Long Z_DEFAULT = 0L;

    public LocationBuilder() {
        setValuesAsDefault();
    }

    private void setValuesAsDefault(){
        x = X_DEFAULT;
        y = Y_DEFAULT;
        z = Z_DEFAULT;
    }
    public LocationBuilder setX(String x){
        try {
            Validator.checkLocationX(x);
        } catch (DataErrorException e){
            return this;
        }
        this.x = Double.parseDouble(x);
        return this;
    }
    public LocationBuilder setY(String y){
        try {
            Validator.checkLocationY(y);
        } catch (DataErrorException e){
            return this;
        }
        this.y = Double.parseDouble(y);
        return this;
    }
    public LocationBuilder setZ(String z){
        try {
            Validator.checkLocationZ(z);
        } catch (DataErrorException e){
            return this;
        }
        this.z = Long.parseLong(z);
        return this;
    }

    public Location build(){
        if (x == X_DEFAULT && y == Y_DEFAULT && z.equals(Z_DEFAULT)) {
            return null;
        }
        Location location = new Location(x,y,z);
        setValuesAsDefault();
        return location;
    }
}
