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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public String toCsv() {
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb
                .append(x).append(del).append(y).append(del).append(z);
        return sb.toString();
    }

    public static String toCsvDefault(){
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb
                .append(LocationBuilder.X_DEFAULT).append(del)
                .append(LocationBuilder.Y_DEFAULT).append(del)
                .append(LocationBuilder.Z_DEFAULT);
        return sb.toString();
    }
}
