package com.devbridge.squares;

import java.util.List;

public interface PointService {

    boolean add(Point point);
    
    boolean addAll(List<Point> points);
    
    boolean remove(Point point);

    int size();

    void removeAll();

    List<Square> calculateSquares();
}
