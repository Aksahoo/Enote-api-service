package com.ensat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensat.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	
}