package com.ensat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ensat.dto.CategoryDto;

@Service
public interface CategoryService {

	public boolean saveCategory(CategoryDto categoryDto);

	public List<CategoryDto> getAllCategory();
	
	public List<CategoryDto> getActiveCategory();

	public CategoryDto getById();

}
