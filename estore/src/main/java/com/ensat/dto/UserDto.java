package com.ensat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

	@NotBlank
	private String firstName;

	private String lastName;
	 @Email(message = "invalid email address")
	@NotNull
	private String email;
	 @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number entered ")
	private String mobNo;
	
	private String password;
}
