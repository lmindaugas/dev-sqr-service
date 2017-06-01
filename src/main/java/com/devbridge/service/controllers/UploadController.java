package com.devbridge.service.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:3002" })
@RestController
public class UploadController {

    @PutMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {
            System.out.println("=== Uplaod the points ===");
            
            String f = new String(file.getBytes());
            List<String> parts = Arrays.asList(f.split("[\n]"));
            parts.forEach(part -> System.out.println("points: " + part));

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