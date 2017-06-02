package com.devbridge.service.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devbridge.squares.Point;
import com.devbridge.squares.PointService;
import com.devbridge.squares.PointServiceImpl;
import com.devbridge.squares.Square;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3001" })
@RestController
public class PointsController {

    // make injectable
    private PointService service = new PointServiceImpl();

    private Map<String, List<Point>> lists = new HashMap<>();

    public PointsController() {
    }

    @GetMapping("/points")
    public List<Point> getPoints() {
        System.out.println("Get the points" + service.getPoints());

        return service.getPoints();
    }

    @GetMapping("/squares")
    public List<String> getSquares() {
        List<String> squares = new ArrayList<>();

        List<Square> calculatedSquares = service.calculateSquares();

        calculatedSquares.forEach(square -> squares.add(square.toString()));

        System.out.println("Get the squares" + squares);

        return squares;
    }

    @PostMapping("/add")
    public void addPoint(@RequestParam("x") int x, @RequestParam("y") int y) {
        System.out.println("Add a point: (" + x + ", " + y + ")");

        service.add(new Point(x, y));
    }

    @DeleteMapping("/clear")
    public void clearPoints() {
        System.out.println("Delete all points");

        service.clear();
    }

    @DeleteMapping("/remove")
    public void removePoint(@RequestParam("x") int x, @RequestParam("y") int y) {
        System.out.println("Remove a point: (" + x + ", " + y + ")");

        service.remove(new Point(x, y));
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/uploadStatus";
        }

        try {
            System.out.println("Upload the points");

            service.clear();

            String f = new String(file.getBytes());
            List<String> parts = Arrays.asList(f.split("[\n]"));

            parts.forEach(point -> {
                try {
                    service.add(new Point(point));
                } catch (Exception e) {
                    e.getMessage();
                }
            });

            System.out.println("Total: " + service.size() + " points have been uploaded");

            redirectAttributes.addFlashAttribute("message", "File has been successfully uploaded");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    @GetMapping("/lists")
    public List<String> getList() {
        System.out.println("Get all the lists");

        return new ArrayList<String>(lists.keySet()); // return set instead
    }

    @GetMapping("/list")
    public List<Point> getList(@RequestParam("list") String name) {
        System.out.println("Get the list" + name);

        return lists.getOrDefault(name, new ArrayList<>());
    }

    @PostMapping("/save")
    public void saveList(@RequestParam("list") String name) {
        System.out.println("Save the list" + name);

        List<Point> list = lists.getOrDefault(name, new ArrayList<>());
        list.clear();
        list.addAll(service.getPoints());
        lists.put(name, list);
    }

    @DeleteMapping("/removeList")
    public void removeList(@RequestParam("list") String name) {
        System.out.println("Remove a list: " + name);

        lists.remove(name);
    }

}
