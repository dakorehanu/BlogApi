package com.Phoenix.blogApis.Service;

import java.util.List;

import com.Phoenix.blogApis.Payloads.PostDto;
import com.Phoenix.blogApis.Payloads.PostResponse;

public interface PostService {

	
	//create
	
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	
	//update
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	
	void deletePost(Integer postId);
	
	//get all posts
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize, String sortBy,String sortDir);
	
	//get single Post
	
	PostDto getPostById(Integer postId);
	
	//get all posts By category
	
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all posts By User
	
	List<PostDto> getPostByUser(Integer userId);
	
	
	
	
}
