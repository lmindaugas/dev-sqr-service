package com.devbridge.squares;

import java.util.List;

public class Square {

    private List<Point> points;

    public Square(List<Point> points) {
        if (points.size() != 4) {
            throw new IllegalArgumentException("Square should have four points, not: " + points.size());
        }
        this.points = points;
    }

    @Override
    public String toString() {
        return points.toString();
    }
    
}
