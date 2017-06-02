package com.devbridge.service.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devbridge.squares.Point;
import com.devbridge.squares.PointService;
import com.devbridge.squares.Square;
import com.devbridge.squares.ValidationService;

@RestController
public class PointsController {

    @Autowired
    private PointService pointService;

    @Autowired
    private ValidationService validationService;

    @GetMapping("/points")
    public List<Point> getPoints() {
        System.out.println("Get all points: " + pointService.getPoints());

        return pointService.getPoints();
    }

    @GetMapping("/squares")
    public List<String> getSquares() {
        List<String> squares = new ArrayList<>();

        List<Square> calculatedSquares = pointService.calculateSquares();

        calculatedSquares.forEach(square -> squares.add(square.toString()));

        System.out.println("Get all squares: " + squares);

        return squares;
    }

    @PostMapping("/add")
    public void addPoint(@RequestParam("x") int x, @RequestParam("y") int y) {
        System.out.println("Add a point: (" + x + ", " + y + ")");
        
        Point point = new Point(x, y);
        
        if(validationService.isValidRange(point)){
            pointService.add(point);
        }
    }

    @DeleteMapping("/clear")
    public void clearPoints() {
        System.out.println("Delete all points");

        pointService.clear();
    }

    @DeleteMapping("/remove")
    public void removePoint(@RequestParam("x") int x, @RequestParam("y") int y) {
        System.out.println("Remove a point: (" + x + ", " + y + ")");

        pointService.remove(new Point(x, y));
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
