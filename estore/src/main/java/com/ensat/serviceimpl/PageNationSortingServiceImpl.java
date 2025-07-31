package com.ensat.serviceimpl;


import java.util.List;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ensat.dto.CategoryDto;
import com.ensat.entity.Category;
import com.ensat.repository.CategoryRepository;
import com.ensat.service.PagenationSortingService;

@Service
public class PageNationSortingServiceImpl implements PagenationSortingService {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryDto> getAllCategory() {
		// TODO Auto-generated method stub
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDto> categoryDtoList = categories.stream().map(cat -> mapper.map(cat, CategoryDto.class)).toList();
		return categoryDtoList;
	}
	@Override
	public List<CategoryDto> sortingWithFiled(String filed){
		//categoryRepository.findAll(Sort.by(filed));
		List<Category> categortsort = categoryRepository.findAll(Sort.by(Sort.Direction.ASC,filed));
		List<CategoryDto> categorydato = categortsort.stream().map(cat-> mapper.map(cat, CategoryDto.class)).toList();
		return categorydato;
	}

	
	public List<CategoryDto> findCategorypagenation(int offset, int pagesize){
		Page<Category> allcategory = categoryRepository.findAll(PageRequest.of(offset, pagesize));
		List<CategoryDto> categoryDto = allcategory.stream().map(cat-> mapper.map(cat, CategoryDto.class)).toList();
		return categoryDto;
		
	}
	
	
	public List<CategoryDto> findProductsWithPaginationAndSorting(int offset,int pageSize,String field){
	         Page<Category> findAller = categoryRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
	         List<CategoryDto> categoryDto = findAller.stream().map(cat->mapper.map(cat, CategoryDto.class)).toList();
	         return  categoryDto;
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
