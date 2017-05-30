package com.devbridge.squares;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class PointServiceTest {

    private static final String X_VALUE_NEG_1 = "-1";
    private static final String X_VALUE_NEG_2 = "-2";
    private static final String X_VALUE_0 = "0";
    private static final String X_VALUE_1 = "1";
    private static final String X_VALUE_2 = "2";
    private static final String Y_VALUE_0 = "0";
    private static final String Y_VALUE_1 = "1";
    private static final String Y_VALUE_2 = "2";
    private static final String Y_VALUE_3 = "3";
    private static final String Y_VALUE_4 = "4";
    private static final String Y_VALUE_NEG_1 = "-1";
    private static final String Y_VALUE_NEG_2 = "-2";

    private PointService service;

    @Before
    public void initService() {
        service = new PointServiceImpl();
    }

    @Test
    public void testPointDuplicates() {

        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_2));
        boolean added = service.add(new Point(X_VALUE_NEG_2, Y_VALUE_2));

        assertFalse(added);
    }

    @Test
    public void testTotalPointSize() {
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_2));
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_2));
        service.add(new Point(X_VALUE_2, Y_VALUE_2));

        assertEquals(2, service.size());
    }

    @Test
    public void testRemovePoint() {
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_2));
        boolean removed = service.remove(new Point(X_VALUE_NEG_2, Y_VALUE_2));

        assertTrue(removed);
    }

    @Test
    public void testRemoveAllPoints() {

        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_2));
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_3));
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_4));

        service.removeAll();

        assertTrue(service.size() == 0);
    }

    @Test
    public void testCalculateSquares() {
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_2));
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_1));
        
        service.add(new Point(X_VALUE_NEG_1, Y_VALUE_1));
        service.add(new Point(X_VALUE_NEG_1, Y_VALUE_2));

        service.add(new Point(X_VALUE_1, Y_VALUE_2));
        service.add(new Point(X_VALUE_1, Y_VALUE_1));
        
        service.add(new Point(X_VALUE_0, Y_VALUE_1));
        service.add(new Point(X_VALUE_0, Y_VALUE_2));

        service.add(new Point(X_VALUE_2, Y_VALUE_NEG_2));
        service.add(new Point(X_VALUE_2, Y_VALUE_2));
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_NEG_2));

        service.add(new Point(X_VALUE_NEG_1, Y_VALUE_0));
        service.add(new Point(X_VALUE_NEG_1, Y_VALUE_NEG_2));
        
        service.add(new Point(X_VALUE_1, Y_VALUE_0));
        service.add(new Point(X_VALUE_1, Y_VALUE_NEG_2));
        
        List<Square> squares = service.calculateSquares();

        squares.forEach(square -> System.out.println(square));

        assertEquals(7, squares.size());
    }
    
    @Test
    public void testCalculateFullGridSquares() {
        
        // 27
        
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_2));
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_1));
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_0));
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_NEG_1));
        service.add(new Point(X_VALUE_NEG_2, Y_VALUE_NEG_2));
        
        service.add(new Point(X_VALUE_NEG_1, Y_VALUE_2));
        service.add(new Point(X_VALUE_NEG_1, Y_VALUE_1));
        service.add(new Point(X_VALUE_NEG_1, Y_VALUE_0));
        service.add(new Point(X_VALUE_NEG_1, Y_VALUE_NEG_1));
        service.add(new Point(X_VALUE_NEG_1, Y_VALUE_NEG_2));
        
        service.add(new Point(X_VALUE_0, Y_VALUE_2));
        service.add(new Point(X_VALUE_0, Y_VALUE_1));
        service.add(new Point(X_VALUE_0, Y_VALUE_0));
        service.add(new Point(X_VALUE_0, Y_VALUE_NEG_1));
        service.add(new Point(X_VALUE_0, Y_VALUE_NEG_2));
        
        service.add(new Point(X_VALUE_1, Y_VALUE_2));
        service.add(new Point(X_VALUE_1, Y_VALUE_1));
        service.add(new Point(X_VALUE_1, Y_VALUE_0));
        service.add(new Point(X_VALUE_1, Y_VALUE_NEG_1));
        service.add(new Point(X_VALUE_1, Y_VALUE_NEG_2));
        
        service.add(new Point(X_VALUE_2, Y_VALUE_2));
        service.add(new Point(X_VALUE_2, Y_VALUE_1));
        service.add(new Point(X_VALUE_2, Y_VALUE_0));
        service.add(new Point(X_VALUE_2, Y_VALUE_NEG_1));
        service.add(new Point(X_VALUE_2, Y_VALUE_NEG_2));
        
        List<Square> squares = service.calculateSquares();
        
        assertEquals(30, squares.size());
    }

    @Ignore
    @Test
    public void testCalculateDistance() {
        Point pt1 = new Point(X_VALUE_NEG_2, Y_VALUE_1);
        Point pt2 = new Point(X_VALUE_NEG_2, Y_VALUE_2);
        Point pt3 = new Point(X_VALUE_NEG_2, Y_VALUE_3);
        Point pt4 = new Point(X_VALUE_NEG_2, Y_VALUE_4);
        Point pt5 = new Point(X_VALUE_1, Y_VALUE_2);
        Point pt6 = new Point(X_VALUE_1, Y_VALUE_NEG_1);
        Point pt7 = new Point(X_VALUE_NEG_2, Y_VALUE_1);
        Point pt8 = new Point(X_VALUE_2, Y_VALUE_1);

        int dist1 = calculateDistance(pt1, pt2);
        int dist2 = calculateDistance(pt1, pt3);
        int dist3 = calculateDistance(pt1, pt4);
        int dist4 = calculateDistance(pt5, pt6);
        int dist5 = calculateDistance(pt7, pt8);
        int dist6 = calculateDistance(pt8, pt7);

        assertEquals(1, dist1);
        assertEquals(2, dist2);
        assertEquals(3, dist3);
        assertEquals(3, dist4);
        assertEquals(4, dist5);
        assertEquals(4, dist6);
    }

    private int calculateDistance(Point pt1, Point pt2) {
        return (int) Math.sqrt(Math.pow(pt2.getX() - pt1.getX(), 2) + Math.pow(pt2.getY() - pt1.getY(), 2));
    }

    @Ignore
    @Test
    public void sortedMap() {
        Map<Integer, Object> sorted = new TreeMap<>();
        sorted.put(-2, null);
        sorted.put(-1, null);
        sorted.put(4, null);
        sorted.put(-5001, null);
        sorted.put(-3, null);
        sorted.put(0, null);

        System.out.println(sorted);
    }

    @Ignore
    @Test
    public void sortAList() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(X_VALUE_NEG_2, Y_VALUE_4));
        points.add(new Point(X_VALUE_NEG_2, Y_VALUE_3));
        points.add(new Point(X_VALUE_NEG_2, Y_VALUE_2));
        points.add(new Point(X_VALUE_NEG_2, Y_VALUE_1));

        Collections.sort(points, (Point pt1, Point pt2) -> Integer.compare(pt2.getY(), pt1.getY()));

        System.out.println(points);
    }

}
