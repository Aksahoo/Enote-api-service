package com.ensat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.util.CommonUtil;
import com.ensat.dto.CategoryDto;
import com.ensat.service.CategoryService;



@RestController
@RequestMapping("/ca")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@PostMapping("/savecategory")
	public ResponseEntity<?> cratecategory(@RequestBody CategoryDto categoryDto) {
		boolean saveCategory = categoryService.saveCategory(categoryDto);
		if (saveCategory) {
			return CommonUtil.createBuildResponseMessage("saved successfully", HttpStatus.CREATED);
		} else {
			return CommonUtil.createErrorResponseMessage("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/listCategory")
	public ResponseEntity<?> getlistCategory() {
		List<CategoryDto> getlistcategory = categoryService.getAllCategory();
		/*
		 * if (CollectionUtils.isEmpty(getlistcategory)) { return
		 * ResponseEntity.noContent().build(); } else {
		 */
		return CommonUtil.createBuildResponse(getlistcategory, HttpStatus.OK);
		}
	

	@GetMapping("/activecategory")
	public ResponseEntity<?> activeCategory() {
		List<CategoryDto> activeCategory = categoryService.getActiveCategory();
		/*
		 * if (CollectionUtils.isEmpty(activeCategory)) { return
		 * ResponseEntity.noContent().build(); } else {
		 */
		return CommonUtil.createBuildResponse(activeCategory, HttpStatus.OK);
		}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getfegrByid(@PathVariable Integer id) throws Exception  {
	CategoryDto categoryDto = categoryService.getbycategoryId(id);

		if (ObjectUtils.isEmpty(categoryDto)) {
			return new ResponseEntity<>("Category not found with Id=" + id, HttpStatus.NOT_FOUND);
		}
		return CommonUtil.createBuildResponse(categoryDto, HttpStatus.OK);

	}
	@DeleteMapping("/{id}")
	 public ResponseEntity<?> deletebyId(@PathVariable Integer id){
		Boolean deletbyid = categoryService.deletbyid(id);
		if (deletbyid) {
			return CommonUtil.createErrorResponseMessage("Category deleted success", HttpStatus.OK);
		}
		return new ResponseEntity<>("Category Not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
		 
	 }
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody CategoryDto categoryDto){
	
          Boolean updateCategory = categoryService.updateCategory(id, categoryDto);	
		return new ResponseEntity<>(updateCategory, HttpStatus.OK);
		
	}
}
