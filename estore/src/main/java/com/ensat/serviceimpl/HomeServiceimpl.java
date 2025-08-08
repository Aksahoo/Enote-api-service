package com.ensat.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensat.entity.User;
import com.ensat.entity.UserStatus;
import com.ensat.execption.ResourceNotFoundException;
import com.ensat.repository.UserRepository;
import com.ensat.service.HomeService;
@Service
public class HomeServiceimpl implements HomeService{
	@Autowired
	private UserRepository userRepo;
	
	public boolean  verifyuser(Integer UserId, String verficationCode) throws Exception {
		
		 //userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("invalid user"));
	User	 user = userRepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("invalid user")); 
		if(user.getUserStatuss().getVerficationCode()==null) {
			throw new SuccessException("Account alredy exist");
		}
	
			if(user.getUserStatuss().getVerficationCode().equals(verficationCode)){
	UserStatus status=user.getUserStatuss();
	            status.setIsActive(true);
	            status.setVerficationCode(null);
	            
		userRepo.save(user);
	
		return true;
		
	}
	return false;
	
	}
}

