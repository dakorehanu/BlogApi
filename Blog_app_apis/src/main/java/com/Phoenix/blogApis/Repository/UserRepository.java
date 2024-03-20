package com.Phoenix.blogApis.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Phoenix.blogApis.Entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>{

	
	Optional<User>  findByUserEmail(String userEmail);
}
