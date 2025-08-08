package com.ensat.service;

import org.springframework.stereotype.Service;

import com.ensat.dto.UserDto;

@Service
public interface UserService {
	
	public boolean  createUser(UserDto userDto) throws Exception;
	
}
