package com.ensat.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class ImageController {

	
	    //@Value("${file.upload-dir}")
	    private String uploadDir;

	    @PostMapping("/images")
	    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
	        try {
	            // Save the file to the directory
	        	String contentType = file.getContentType();
	        	if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
	        	    throw new IllegalArgumentException("Only JPEG or PNG images are allowed");
	        	}
	        	file.getName();
	            String filePath = saveImage(file);
	            return ResponseEntity.ok("Image uploaded successfully: " + filePath);
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
	        }
	    }

	    private String saveImage(MultipartFile file) throws IOException {
	        Path uploadPath = Paths.get(uploadDir);
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }
	        
	        String fileName = file.getOriginalFilename();
	        Path filePath = uploadPath.resolve(fileName);
	        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        return filePath.toString();
	    }
	   
	}


