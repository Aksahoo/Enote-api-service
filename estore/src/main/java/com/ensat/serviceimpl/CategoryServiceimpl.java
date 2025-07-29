package com.ensat.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ensat.dto.CategoryDto;
import com.ensat.entity.Category;
import com.ensat.repository.CategoryRepository;
import com.ensat.service.CategoryService;

@Service
public class CategoryServiceimpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper mapper;

	@Override
	public boolean saveCategory(CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		// Category Category =mapper.map(categoryDto, Category.class);

		// Category category = mapper.map(categoryDto, Category.class);
          Category category = mapper.map(categoryDto, Category.class);
		Category savecategory = categoryRepository.save(category);
		if (ObjectUtils.isEmpty(savecategory)) {
			return false;
		}
		return true;
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
		return categoryDtoList;
	}

	@Override
	public List<CategoryDto> getActiveCategory() {
		List<Category> findByIsActiveTrue = categoryRepository.findByIsActiveTrue();
		List<CategoryDto> activecategory = findByIsActiveTrue.stream().map(cat ->mapper.map(cat, CategoryDto.class)).toList();
		return activecategory;
	}

	@Override
	public CategoryDto getbycategoryId(Integer id) {
		Optional<Category> findByCatgeory = categoryRepository.findById(id);
		if (findByCatgeory.isPresent()) {
			Category category = findByCatgeory.get();
			return mapper.map(category, CategoryDto.class);
		}
		return null;
	}
		

	
	

}
