package org.example.data;

/**
 * Класс координат
 */
public class Coordinates {
    private long x;
    private double y;

    public Coordinates(long x, double y) {
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X: " + x +
                ", Y: " + y;
    }

    /**
     * @return строка для записи в csv
     */
    public String toCsv() {
        char del = ',';
        StringBuilder sb = new StringBuilder();
        sb.append(x).append(del).append(y);
        return sb.toString();
    }
}
