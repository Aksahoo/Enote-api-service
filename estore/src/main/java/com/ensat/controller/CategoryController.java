package com.ensat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ensat.dto.CategoryDto;
import com.ensat.service.CategoryService;

@RestController
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/savecategory")
	public ResponseEntity<?> cratecategory(@RequestBody CategoryDto categoryDto) {
		boolean saveCategory = categoryService.saveCategory(categoryDto);
		if (saveCategory) {
			return new ResponseEntity<>("saved successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listCategory")
	public ResponseEntity<?> getlistCategory(){
		List<CategoryDto> getlistcategory = categoryService.getAllCategory();
		if(CollectionUtils.isEmpty(getlistcategory)) {
			return ResponseEntity.noContent().build();
		}
		else {
			return new ResponseEntity<>(getlistcategory, HttpStatus.OK);
		}
	}
	@GetMapping("/activecategory")
	public ResponseEntity<?> activeCategory(){
	List<CategoryDto> activeCategory = categoryService.getActiveCategory();
	 if(CollectionUtils.isEmpty(activeCategory)) {
	return ResponseEntity.noContent().build();
	 }
	else {
		return new ResponseEntity<>(activeCategory, HttpStatus.OK);
	}
	 }
	 
	}
		
	
