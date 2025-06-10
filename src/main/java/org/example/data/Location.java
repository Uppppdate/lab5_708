package org.example.data;

/**
 * Класс Location
 */
public class Location {
    private Long id;
    private double x;
    private double y;
    private Long z;

    public Location(double x, double y, Long z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Long getZ() {
        return z;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "X: " + x +
                ", Y: " + y +
                ", Z: " + z;
    }

    /**
     * @return строковое представление для записи в csv файл
     */
    public String toCsv() {
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb
                .append(x).append(del).append(y).append(del).append(z);
        return sb.toString();
    }

    /**
     * @return строковое представление для записи в csv по умолчанию
     */
    public static String toCsvDefault() {
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb
                .append(LocationBuilder.X_DEFAULT).append(del)
                .append(LocationBuilder.Y_DEFAULT).append(del)
                .append(LocationBuilder.Z_DEFAULT);
        return sb.toString();
    }
}
