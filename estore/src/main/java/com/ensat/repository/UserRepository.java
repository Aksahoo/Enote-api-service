package com.ensat.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ensat.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	public boolean existsByEmail(String email);
	       //Boolean existsByEmail(String email);

}