package com.devbridge.service.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devbridge.squares.Point;
import com.devbridge.squares.PointService;
import com.devbridge.squares.PointServiceImpl;
import com.devbridge.squares.ValidationService;
import com.devbridge.squares.ValidationServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3002" })
@RestController
public class PointsController {

    // make injectable
    PointService service = new PointServiceImpl();

    public PointsController() {

        // dummy data
        // ArrayList<Point> points = new ArrayList<>();
        // for (int i = 0; i < 10; i++) {
        // points.add(new Point((int) (Math.random() * 10), (int) (Math.random() * 10)));
        // }
        // service.addAll(points);
    }

    @GetMapping("/points")
    public List<Point> getPoints() {

        System.out.println("=== get the points ===" + service.getPoints());

        return service.getPoints();
    }

    @PutMapping("/add")
    public void addPoint(@RequestParam("x") int x, @RequestParam("y") int y) {

        System.out.println("=== add a point: (" + x + ", " + y + ") ===");

        service.add(new Point(x, y));
    }

    @DeleteMapping("/delete")
    public void deletePoints() {

        System.out.println("=== Delete all the points ===");

        service.clear();
    }

    @DeleteMapping("/remove")
    public void removePoint(@RequestParam("x") int x, @RequestParam("y") int y) {

        System.out.println("=== remove a point: (" + x + ", " + y + ") ===");

        service.remove(new Point(x, y));
    }

    @PutMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            System.out.println("=== Upload the points ===");

            String f = new String(file.getBytes());
            List<String> parts = Arrays.asList(f.split("[\n]"));

            parts.forEach(point -> {
                try {
                    service.add(new Point(point));
                } catch (Exception e) {
                    e.getMessage();
                }
            });

            System.out.println("total: " + service.size() + " points have been uploaded");

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

    @GetMapping("/test")
    public String getTest() {
        return "test";
    }

}
