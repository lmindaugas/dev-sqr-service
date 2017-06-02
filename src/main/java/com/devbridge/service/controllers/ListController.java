package com.devbridge.service.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devbridge.squares.Point;
import com.devbridge.squares.PointService;

@RestController
public class ListController {

    @Autowired
    private PointService pointService;

    private Map<String, List<Point>> lists = new HashMap<>();

    @GetMapping("/lists")
    public List<String> getList() {
        System.out.println("Get all the lists");

        return new ArrayList<String>(lists.keySet()); // return set instead
    }

    @GetMapping("/list")
    public List<Point> getList(@RequestParam("list") String name) {
        System.out.println("Get the list: " + name);

        // get stored list
        List<Point> storedPoints = lists.getOrDefault(name, new ArrayList<>());
        // replace existing points
        pointService.replace(storedPoints);

        return storedPoints;
    }

    @PostMapping("/save")
    public void saveList(@RequestParam("list") String name) {
        System.out.println("Save the list: " + name);

        // get current list and replace with existing points
        List<Point> list = lists.getOrDefault(name, new ArrayList<>());
        list.clear();
        list.addAll(pointService.getPoints());
        lists.put(name, list);
    }

    @DeleteMapping("/removeList")
    public void removeList(@RequestParam("list") String name) {
        System.out.println("Remove a list: " + name);

        lists.remove(name);
    }
}
