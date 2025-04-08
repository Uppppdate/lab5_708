package org.example.data;

public class Location {
    private double x;
    private double y;
    private Long z; //Поле не может быть null

    public Location(double x, double y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
