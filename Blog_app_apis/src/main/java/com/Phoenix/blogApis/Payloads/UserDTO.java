package com.Phoenix.blogApis.Payloads;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
public class UserDTO {

	
	private Integer userId;
	
	@NotEmpty
	@Size(min = 5, message = "Username must be min of 5 character")
	private String userName;
	
	@Email(message = "Email address is not valid")
	private String userEmail;
	
	@NotEmpty
	@Size(min = 4, max = 10, message = "password must be min of 4 character and max of 10 character")
	private String userPassword;
	
	@NotBlank
	private String aboutUser;

	
	
}
