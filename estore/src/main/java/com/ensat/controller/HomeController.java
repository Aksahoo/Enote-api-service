package com.ensat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ensat.service.HomeService;
import com.ensat.util.CommonUtil;
@RequestMapping("/home")
@RestController
public class HomeController {
	@Autowired
	private HomeService homeService;
	
	@GetMapping("/verify")
	public ResponseEntity<?> veryfyuser(@RequestParam Integer uid, @RequestParam String code) throws Exception{
		
		boolean verifyuser = homeService.verifyuser(uid, code);
		if (verifyuser)
			return CommonUtil.createBuildResponseMessage("Account verification success", HttpStatus.OK);
		return CommonUtil.createErrorResponseMessage("Invalid Verification link", HttpStatus.BAD_REQUEST);
		
	}
	
	

}
