package com.Phoenix.blogApis.Service;

import java.util.List;

import com.Phoenix.blogApis.Payloads.UserDTO;

public interface UserServiceI {

	
	
	UserDTO createUser(UserDTO user);
	
	UserDTO updateUser(UserDTO user,Integer userId);
	
	UserDTO getUserById (Integer userId);
	
	List<UserDTO> getallUsers();
	
	void deleteUser (Integer userId);
}
