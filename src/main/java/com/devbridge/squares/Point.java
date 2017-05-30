package com.devbridge.squares;

public class Point {

    private int x;
    private int y;

    public Point(String x, String y) {
        try {
            this.x = Integer.parseInt(x);
            this.y = Integer.parseInt(y);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Incorrect point format");
        }
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
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
