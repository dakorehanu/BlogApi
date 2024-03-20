package com.Phoenix.blogApis.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Phoenix.blogApis.Entity.User;
import com.Phoenix.blogApis.Exceptions.ResourceNotFoundException;
import com.Phoenix.blogApis.Repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

	
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email"+username,0));
		
		return user;
	}

}
