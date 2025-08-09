package com.ensat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.becoder.util.CommonUtil;
import com.ensat.dto.UserDto;
import com.ensat.execption.ExistDataException;
import com.ensat.repository.UserRepository;
import com.ensat.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/registation")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) throws Exception{
		
		boolean createUser = userService.createUser(userDto);
		if(createUser) {
			return CommonUtil.createBuildResponseMessage("Userr Registation SucessFully", HttpStatus.CREATED);
		} else {
			return CommonUtil.createErrorResponseMessage("not saved", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
