package com.devbridge.service.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devbridge.squares.Point;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3002" })
@RestController
public class PointsController {

    private static List<Point> points = new ArrayList<>();

    static {
        for (int i = 0; i < 1000; i++) {
            points.add(new Point((int) (Math.random() * 10), (int) (Math.random() * 10)));
        }
    }

    @GetMapping("/points")
    public List<Point> getPoints() {

        System.out.println("=== get the points ===" + points);

        return points;
    }

    @PutMapping("/add")
    public void addPoint(@RequestParam("x") int x, @RequestParam("y") int y) {

        System.out.println("=== add a point: (" + x + ", " + y + ") ===");

        points.add(new Point(x, y));
    }

    @DeleteMapping("/delete")
    public void deletePoints() {

        System.out.println("=== Delete all the points ===");

        points.clear();
    }

}
