package com.devbridge.squares;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValidationServiceTest {
    
    private static final String X_VALUE_NEG_2 = "-2";
    private static final String Y_VALUE_2 = "2";
    
    private static final String X_VALUE_NEG_5001 = "-5001";
    private static final String Y_VALUE_5001 = "5001";
    private ValidationService service;
    

    @Before
    public void initValidationService(){
        service = new ValidationServiceImpl();
    }
    
    @Test
    public void testRangeValidation(){
        
        Point pointValid = new Point(X_VALUE_NEG_2, Y_VALUE_2);
        Point pointInvalidX = new Point(X_VALUE_NEG_5001, Y_VALUE_2);
        Point pointInvalidY = new Point(X_VALUE_NEG_2, Y_VALUE_5001);
        Point pointInvalidBoth = new Point(X_VALUE_NEG_5001, Y_VALUE_5001);
        
        Assert.assertTrue(service.isValidRange(pointValid));
        Assert.assertFalse(service.isValidRange(pointInvalidX));
        Assert.assertFalse(service.isValidRange(pointInvalidY));
        Assert.assertFalse(service.isValidRange(pointInvalidBoth));
    }
    
}
