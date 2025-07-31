package com.ensat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ensat.dto.CategoryDto;

@Service
public interface CategoryService {

	public boolean saveCategory(CategoryDto categoryDto);

	public List<CategoryDto> getAllCategory();
	
	public List<CategoryDto> getActiveCategory();

	public CategoryDto getbycategoryId(Integer id) throws Exception;

	public Boolean  deletbyid(Integer id);
	
	public CategoryDto getCategoryByIds(Integer id);
	
	public Boolean updateCategory(Integer id,CategoryDto categoryDto);



}
