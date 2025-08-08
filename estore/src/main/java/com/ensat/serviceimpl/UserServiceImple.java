package com.ensat.serviceimpl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ensat.dto.EmailRequest;
import com.ensat.dto.UserDto;
import com.ensat.entity.User;
import com.ensat.entity.UserStatus;
import com.ensat.execption.ExistDataException;
import com.ensat.repository.UserRepository;
import com.ensat.service.UserService;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EmailService emailService;

	@Override
	public boolean createUser(UserDto userDto) throws Exception {
		Boolean existEmail = userRepository.existsByEmail(userDto.getEmail());
		if (existEmail) {
			throw new ExistDataException("EMailid Exist");
		}

		User user = mapper.map(userDto, User.class);
		UserStatus status = UserStatus.builder().isActive(false).verficationCode(UUID.randomUUID().toString()).build();
		user.setUserStatuss(status);

		User saveUser = userRepository.save(user);
		if (!ObjectUtils.isEmpty(saveUser)) {
			// send email
			emailSend(saveUser); 
			return true; 
		}
		return false;
	}

	@SuppressWarnings("unused")
	private void emailSend(User saveUser) throws Exception {


	    String message = "Hi,<b>[[username]]</b> "
	            + "<br> Your account registered successfully.<br>"
	            + "<br> Click the below link to verify & activate your account:<br>"
	            + "<a href='[[url]]'>Click Here</a> <br><br>"
	            + "Thanks,<br>Enotes.com";

	    // Replace placeholders
	    message = message.replace("[[username]]", saveUser.getFirstName());
	   message= message.replace("[[url]]",
	            "http://localhost:8080/home/verify?uid=" + saveUser.getUserId()
	                    + "&&code=" + saveUser.getUserStatuss().getVerficationCode());

		EmailRequest emailRequest = EmailRequest.builder().to(saveUser.getEmail())
				.title("Account Creating Confirmation").subject("Account Created Success").message(message).build();
		emailService.sendEmail(emailRequest);
	}

}
