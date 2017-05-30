package com.devbridge.squares;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {

    private static final String X_VALUE_NEG_2 = "-2";
    private static final String X_VALUE_NEG_3 = "-3";
    private static final String Y_VALUE_2 = "2";
    private static final String Y_VALUE_3 = "3";

    private static final String X_VALUE_NOT_A_NUMBER = "-2d";
    private static final String Y_VALUE_NOT_A_NUMBER = "e-";
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidCoordinatesValues() {

        new Point(X_VALUE_NOT_A_NUMBER, Y_VALUE_NOT_A_NUMBER);
    }

    @Test
    public void testValidCoordinateValues() {
        
        Point point = new Point(X_VALUE_NEG_2, Y_VALUE_2);
        
        Assert.assertEquals(-2, point.getX());
        Assert.assertEquals(2, point.getY());
    }
    
    @Test
    public void testPointsAreEqual(){
        
        Point point1 = new Point(X_VALUE_NEG_2, Y_VALUE_2);
        Point point2 = new Point(X_VALUE_NEG_2, Y_VALUE_2);
        Point point3 = new Point(X_VALUE_NEG_3, Y_VALUE_3);
        Point point4 = new Point(X_VALUE_NEG_2, Y_VALUE_3);
        
        Assert.assertEquals(point1, point2);
        Assert.assertNotEquals(point1, point3);
        Assert.assertNotEquals(point3, point4);
        
    }
}
