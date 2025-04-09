package org.example.data;

public class CoordinatesBuilder {
    private long x;
    private double y; //Максимальное значение поля: 482

    public CoordinatesBuilder() {
        setValuesAsDefault();
    }

    private void setValuesAsDefault() {
        x = 0;
        y = 0;
    }

    public CoordinatesBuilder setX(long x) {
        this.x = x;
        return this;
    }

    public CoordinatesBuilder setY(double y) {
        if (y > 482) {
            return this;
        }
        this.y = y;
        return this;
    }

    public Coordinates build() {
        Coordinates coordinates = new Coordinates(x, y);
        setValuesAsDefault();
        return coordinates;
    }
}
