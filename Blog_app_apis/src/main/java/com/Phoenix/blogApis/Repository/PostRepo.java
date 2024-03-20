package com.Phoenix.blogApis.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Phoenix.blogApis.Entity.Category;
import com.Phoenix.blogApis.Entity.Post;
import com.Phoenix.blogApis.Entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
}
