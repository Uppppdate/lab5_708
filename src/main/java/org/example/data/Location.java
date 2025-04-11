package org.example.data;

public class Location {
    private double x;
    private double y;
    private Long z;

    public Location(double x, double y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
