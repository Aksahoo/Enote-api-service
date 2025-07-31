package com.ensat.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ensat.dto.CategoryDto;
import com.ensat.entity.Category;
import com.ensat.execption.ExistDataException;
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

		Boolean exist = categoryRepository.existsByName(categoryDto.getName().trim());
		if (exist) {
			throw new ExistDataException("Name already exist");
		}
		Category category = mapper.map(categoryDto, Category.class);

		categoryRepository.save(category);

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
		List<CategoryDto> activecategory = findByIsActiveTrue.stream().map(cat -> mapper.map(cat, CategoryDto.class))
				.toList();
		return activecategory;
	}

	@Override
	public CategoryDto getbycategoryId(Integer id) throws Exception {
		Optional<Category> findByCatgeory = categoryRepository.findById(id);
		/*
		 * if (findByCatgeory.isPresent()) { Category category = findByCatgeory.get();
		 */
		return mapper.map(findByCatgeory, CategoryDto.class);
		// }
		// return null;
	}

	@Override
	public Boolean deletbyid(Integer id) {
		Optional<Category> findByCatgeory = categoryRepository.findById(id);
		if (findByCatgeory.isPresent()) {
			Category category = findByCatgeory.get();
			category.setIsDeleted(true);
			categoryRepository.save(category);
			return true;
		}
		return false;
	}

	public CategoryDto getCategoryByIds(Integer id) {

		Optional<Category> findByCatgeory = categoryRepository.findByIdAndIsDeletedFalse(id);
		if (findByCatgeory.isPresent()) {
			Category category = findByCatgeory.get();
			return mapper.map(category, CategoryDto.class);
		}
		return null;
	}

	@Override
	public Boolean updateCategory(Integer id, CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		 Optional<Category> findById = categoryRepository.findById(id);
		/*
		 * Category category = findById.get(); category.setName(categoryDto.getName());
		 * category.setDescription(categoryDto.getDescription());
		 * category.setIsActive(categoryDto.getIsActive());
		 */
		 Category category = mapper.map(categoryDto, Category.class);
		  
		   categoryRepository.save(category);
		   return true;
		 
	}

}
