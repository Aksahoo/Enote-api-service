package com.ensat.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensat.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	List<Category> findByIsActiveTrue();

       public Optional<Category> findByIdAndIsDeletedFalse(Integer id);

	public Boolean existsByName(String name);
}