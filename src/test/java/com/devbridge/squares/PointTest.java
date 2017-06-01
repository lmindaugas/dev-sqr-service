package com.devbridge.squares;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class PointTest {

    private static final String X_VALUE_NEG_2 = "-2";
    private static final String X_VALUE_NEG_3 = "-3";
    private static final String Y_VALUE_2 = "2";
    private static final String Y_VALUE_3 = "3";
    private static final String POINT_3_2 = "3 2";
    private static final String POINT_3_10 = "3 10";
    private static final String POINT_3_10_SPACES = " -3 -10    ";
    private static final String POINT_3_SINGLE = "3 ";
    

    private static final String X_VALUE_NOT_A_NUMBER = "-2d";
    private static final String Y_VALUE_NOT_A_NUMBER = "e-";

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCoordinateValues() {

        new Point(X_VALUE_NOT_A_NUMBER, Y_VALUE_NOT_A_NUMBER);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSingleCoordinateValue() {

        new Point(POINT_3_SINGLE);
    }

    @Test
    public void testValidCoordinateValues() {

        Point point = new Point(X_VALUE_NEG_2, Y_VALUE_2);

        assertEquals(-2, point.getX());
        assertEquals(2, point.getY());
    }

    @Test
    public void testPointsAreEqual() {

        Point point1 = new Point(X_VALUE_NEG_2, Y_VALUE_2);
        Point point2 = new Point(X_VALUE_NEG_2, Y_VALUE_2);
        Point point3 = new Point(X_VALUE_NEG_3, Y_VALUE_3);
        Point point4 = new Point(X_VALUE_NEG_2, Y_VALUE_3);

        assertEquals(point1, point2);
        assertNotEquals(point1, point3);
        assertNotEquals(point3, point4);
    }

    @Test
    public void testPointConstruction() {

        Point point1 = new Point(POINT_3_2);
        Point point2 = new Point(POINT_3_10);
        Point point3 = new Point(POINT_3_10_SPACES);

        assertEquals(point1.getX(), 3);
        assertEquals(point2.getY(), 10);
        assertEquals(point3.getY(), -10);
    }
}
