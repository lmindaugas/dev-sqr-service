package com.devbridge.squares;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PointServiceImpl implements PointService {

    private Map<Integer, List<Point>> table = new TreeMap<>();

    @Override
    public boolean add(Point point) {
        List<Point> points = table.getOrDefault(point.getX(), new ArrayList<>());
        table.put(point.getX(), points);
        if (!points.contains(point)) {
            points.add(point);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean addAll(List<Point> points) {

        clear();
        for (Point point : points) {
            add(point);
        }
        return true;
    }

    @Override
    public int size() {
        return table.values().stream().mapToInt(List::size).sum();
    }

    @Override
    public boolean remove(Point point) {
        return table.get(point.getX()).remove(point);
    }

    @Override
    public void clear() {
        table.clear();
    }

    @Override
    public List<Square> calculateSquares() {

        List<Square> squares = new ArrayList<>();

        for (Integer key : table.keySet()) {
            List<Point> points = table.get(key);
            Collections.sort(points, (Point pt1, Point pt2) -> Integer.compare(pt2.getY(), pt1.getY()));
            if (points.size() > 1) {

                for (int i = 0; i < points.size(); i++) {
                    Point point = points.get(i);
                    for (int j = i + 1; j < points.size(); j++) {
                        int dist = calculateDistance(point, points.get(j));
                        List<Point> neighbours = table.getOrDefault(key + dist, null);
                        if (neighbours != null) {
                            Point pt1 = new Point(key + dist, point.getY());
                            Point pt2 = new Point(key + dist, points.get(j).getY());
                            if (neighbours.contains(pt1) && neighbours.contains(pt2)) {
                                squares.add(new Square(Arrays.asList(point, points.get(j), pt1, pt2)));
                            }
                        }
                    }
                }
            }
        }
        return squares;
    }

    private int calculateDistance(Point pt1, Point pt2) {
        return (int) Math.sqrt(Math.pow(pt2.getX() - pt1.getX(), 2) + Math.pow(pt2.getY() - pt1.getY(), 2));
    }

    @Override
    public List<Point> getPoints() {
        // make immutable
        return table.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

    }

}
