package com.devbridge.squares;

public class Point {

    private int x;
    private int y;

    public Point(String x, String y) {
        try {
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Incorrect point type");
        }
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(String point) {
        String[] parts = point.trim().split("\\s");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Point consists of two numbers");
        }

        try {
            this.x = Integer.parseInt(parts[0]);
            this.y = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect point type: " + point);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {

        Point point = (Point) obj;

        return this.x == point.getX() && this.y == point.getY();
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
