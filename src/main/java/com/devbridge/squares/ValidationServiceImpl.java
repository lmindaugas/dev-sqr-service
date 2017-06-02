package com.devbridge.squares;

import org.springframework.stereotype.Component;

@Component
public class ValidationServiceImpl implements ValidationService {

    private static final int MIN_RANGE_VALUE = -5000;
    private static final int MAX_RANGE_VALUE = 5000;

    public boolean isValidRange(Point point) {
        
        boolean xValid = point.getX() <= MAX_RANGE_VALUE && point.getX() >= MIN_RANGE_VALUE;
        boolean yValid = point.getY() <= MAX_RANGE_VALUE && point.getY() >= MIN_RANGE_VALUE;
        return xValid && yValid;
    }

}
