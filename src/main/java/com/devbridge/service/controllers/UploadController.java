package com.devbridge.service.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devbridge.squares.Point;
import com.devbridge.squares.PointService;

@RestController
public class UploadController {

    @Autowired
    private PointService pointService;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/uploadStatus";
        }

        try {
            System.out.println("Upload the points");

            pointService.clear();

            String f = new String(file.getBytes());
            List<String> parts = Arrays.asList(f.split("[\n]"));

            parts.forEach(point -> {
                try {
                    pointService.add(new Point(point));
                } catch (Exception e) {
                    e.getMessage();
                }
            });

            System.out.println("Total: " + pointService.size() + " points have been uploaded");

            redirectAttributes.addFlashAttribute("message", "File has been successfully uploaded");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }
}
