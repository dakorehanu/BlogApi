package com.Phoenix.blogApis.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.Phoenix.blogApis.Entity.Category;
import com.Phoenix.blogApis.Entity.Post;
import com.Phoenix.blogApis.Entity.User;
import com.Phoenix.blogApis.Exceptions.ResourceNotFoundException;
import com.Phoenix.blogApis.Payloads.PostDto;
import com.Phoenix.blogApis.Payloads.PostResponse;
import com.Phoenix.blogApis.Repository.CategoryRepo;
import com.Phoenix.blogApis.Repository.PostRepo;
import com.Phoenix.blogApis.Repository.UserRepository;
import com.Phoenix.blogApis.Service.PostService;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public PostDto createPost
			(PostDto postDto,Integer userId,
			Integer categoryId)  
	{
		log.info("Intiating dao call for save Post Data");
		User user=
			this.userRepository
			.findById(userId)
			.orElseThrow(()->new ResourceNotFoundException("User", "userid", userId)); 
		Category category=
			this.categoryRepo
			.findById(categoryId)
		    .orElseThrow(()->new ResourceNotFoundException("Category","categoryid",categoryId));		
		Post post=
				this.modelMapper
				.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost=this.postRepo.save(post);
		log.info("completed dao call for save Post Data");
		
		return this.modelMapper.map(newPost, PostDto.class);
	}

	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) 
	{
		log.info("Intiating dao call for get Post Data with:",postId);
		Post post = 
				this.postRepo
				.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "post id",postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setImageName(postDto.getImageName());
		Post post2 = this.postRepo.save(post);
		PostDto postDto2 = 
				this.modelMapper
				.map(post2, PostDto.class);
		log.info("complete dao call for get Post Data with:",postId);
		
		return postDto2;
	}

	
	
	
	@Override
	public void deletePost(Integer postId) 
	{
		log.info("Intiating dao call to delete the Post Data with:",postId);
		Post post = 
				this.postRepo
				.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
				this.postRepo.delete(post);
		log.info("complete dao call to delete Post Data with:",postId);

	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) 
	{
		log.info("Intiating dao call for get all Posts Data");
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			 sort = Sort.by(sortBy).ascending();
		}else {
			sort =Sort.by(sortBy).descending();
		}
			
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		 Page<Post> pagePost = this.postRepo.findAll(p);
		 List<Post> allposts = pagePost.getContent();
		List<PostDto> allpostsdto = 
				allposts.stream()
				.map((post)->
				this.modelMapper
				.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(allpostsdto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		log.info("complete dao call for get all Posts Data ");
		
		return postResponse;
	}

	
	@Override
	public PostDto getPostById(Integer postId) 
	{
		log.info("Intiating dao call to get the Post Data with:",postId);
		Post post = 
				this.postRepo
				.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","postid",postId));
		PostDto postDto = 
				   this.modelMapper
				   .map(post, PostDto.class);			
		log.info("complete dao call to get Post Data with:",postId);
		   return postDto;
	}

	
	
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		log.info("Intiating dao call to get the Post Data with:",categoryId);
		Category cat=
				this.categoryRepo
				.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category","categoryid",categoryId));	
		List<Post> posts = this.postRepo.findByCategory(cat);
		List<PostDto> postDto = 
				posts.stream()
				.map((post)-> 
				this.modelMapper
				.map(post, PostDto.class))
				.collect(Collectors.toList());
		log.info("complete dao call to get Post Data with:",categoryId);

		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		log.info("Intiating dao call to get the Post Data with:",userId);
		User user = 
				this
				.userRepository
				.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","userid",userId));
		List<Post> post2 = this.postRepo.findByUser(user);
		List<PostDto> postDto1 = 
				post2.stream()
				.map((post)-> 
				this.modelMapper
				.map(post, PostDto.class))
				.collect(Collectors.toList());
		log.info("complete dao call to get Post Data with:",userId);

		return postDto1;
	}

}
