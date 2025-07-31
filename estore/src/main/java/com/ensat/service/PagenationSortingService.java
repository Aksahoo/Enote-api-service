package com.ensat.service;

import java.util.List;

import com.ensat.dto.CategoryDto;

public interface PagenationSortingService {
	
	public List<CategoryDto> getAllCategory();
	
	public List<CategoryDto> sortingWithFiled(String filed);
	
	public List<CategoryDto> findCategorypagenation(int offset, int pagesize);
	public List<CategoryDto> findProductsWithPaginationAndSorting(int offset,int pageSize,String field);
}
