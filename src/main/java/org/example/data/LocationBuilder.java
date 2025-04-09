package org.example.data;

public class LocationBuilder {
    private double x;
    private double y;
    private Long z; //Поле не может быть null

    public LocationBuilder() {
        setValuesAsDefault();
    }

    private void setValuesAsDefault(){
        x = 0d;
        y = 0d;
        z = 0L;
    }
    public LocationBuilder setX(double x){
        this.x = x;
        return this;
    }
    public LocationBuilder setY(double y){
        this.y = y;
        return this;
    }
    public LocationBuilder setZ(Long z){
        if(Validator.isNull(z)){
            return this;
        }
        this.z = z;
        return this;
    }

    public Location build(){
        Location location = new Location(x,y,z);
        setValuesAsDefault();
        return location;
    }
}
