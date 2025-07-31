package com.ensat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ensat.dto.CategoryDto;
import com.ensat.service.PagenationSortingService;

@RestController
public class PageNationSortingCotroller {

	@Autowired
	private PagenationSortingService pagenationSortingService;

	@GetMapping("/cat")
	public ResponseEntity<?> getAllCategory() {
		List<CategoryDto> allCategory = pagenationSortingService.getAllCategory();
		if (CollectionUtils.isEmpty(allCategory)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(allCategory, HttpStatus.OK);
		}
	}

	@GetMapping("/{filed}")
	public ResponseEntity<?> sortingwithfiled(@PathVariable String filed) {
		List<CategoryDto> sortingWithFiled = pagenationSortingService.sortingWithFiled(filed);
		if (CollectionUtils.isEmpty(sortingWithFiled)) {
			return ResponseEntity.noContent().build();
		} else {
			return new ResponseEntity<>(sortingWithFiled, HttpStatus.OK);
		}

	}

	@GetMapping("/pagenation/{offset}/{pagesize}")
	public ResponseEntity<?> categorywithpagenation(@PathVariable int offset, @PathVariable int pagesize) {

		List<CategoryDto> findCategorypagenation = pagenationSortingService.findCategorypagenation(offset, pagesize);
		if (CollectionUtils.isEmpty(findCategorypagenation)) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<>(findCategorypagenation, HttpStatus.OK);

	}

	@GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
	public ResponseEntity<?> categoryPagenationWithSort(@PathVariable int offset, @PathVariable int pageSize,
			@PathVariable String field) {
		List<CategoryDto> paginationAndSorting = pagenationSortingService.findProductsWithPaginationAndSorting(offset,
				pageSize, field);
		if (CollectionUtils.isEmpty(paginationAndSorting)) {
			return ResponseEntity.noContent().build();
		}
		return new ResponseEntity<>(paginationAndSorting, HttpStatus.OK);

	}

}
