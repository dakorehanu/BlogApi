package com.Phoenix.blogApis.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Phoenix.blogApis.Entity.User;
import com.Phoenix.blogApis.Exceptions.ResourceNotFoundException;
import com.Phoenix.blogApis.Payloads.UserDTO;
import com.Phoenix.blogApis.Repository.UserRepository;
import com.Phoenix.blogApis.Service.UserServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserServiceI {

	@Autowired
	public UserRepository userRepository;

	@Override
	public UserDTO createUser(UserDTO userDto) 
	{
		log.info("Intiating dao call for save User Data");
		User usr = this.dtoToUser(userDto);
		User saveuser = this.userRepository.save(usr);
		UserDTO dto = this.UserTodto(saveuser);
		log.info("completed dao call for save User Data");
		return dto;
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) 
	{
		log.info("Intiating dao call for get User Data with:",userId);
		User users = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		users.setUserName(userDTO.getUserName());
		users.setUserEmail(userDTO.getUserEmail());
		users.setUserPassword(userDTO.getUserPassword());
		users.setAboutUser(userDTO.getAboutUser());

		User updatedUser = this.userRepository.save(users);
		UserDTO userTodto1 = this.UserTodto(updatedUser);
		log.info("complete dao call for get User Data with:",userId);
		return userTodto1;
	}

	@Override
	public UserDTO getUserById(Integer userId) 
	{
		log.info("Intiating dao call to get the User Data with:",userId);
		User users = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		UserDTO userDTO = this.UserTodto(users);
		log.info("complete dao call to get User Data with:",userId);
		return userDTO;
	}

	@Override
	public List<UserDTO> getallUsers() {
		log.info("Intiating dao call for get all Users Data");
		List<User> Users = this.userRepository.findAll();

		List<UserDTO> UserDto = Users.stream().map(user -> this.UserTodto(user)).collect(Collectors.toList());
		log.info("complete dao call for get all Users Data ");
		return UserDto;
	}

	@Override
	public void deleteUser(Integer userId) 
	{
		log.info("Intiating dao call to delete the User Data with:",userId);
		User user = this.userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepository.delete(user);
		log.info("complete dao call to delete User Data with:",userId);
	}

	public User dtoToUser(UserDTO userdto) {
		User us = new User();
		us.setUserId(userdto.getUserId());
		us.setUserName(userdto.getUserName());
		us.setUserEmail(userdto.getUserEmail());
		us.setUserPassword(userdto.getUserPassword());
		us.setAboutUser(userdto.getAboutUser());
		return us;
	}

	
	public UserDTO UserTodto(User user) {
		UserDTO usdt=new UserDTO();
		usdt.setUserId(user.getUserId());
		usdt.setUserName(user.getUsername());
		usdt.setUserEmail(user.getUserEmail());
		usdt.setUserPassword(user.getUserPassword());
		usdt.setAboutUser(user.getAboutUser());
		

		return usdt;

	}

}
