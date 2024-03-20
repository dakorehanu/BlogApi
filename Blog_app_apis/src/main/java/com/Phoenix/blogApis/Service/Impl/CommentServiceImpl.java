package com.Phoenix.blogApis.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Phoenix.blogApis.Entity.Comment;
import com.Phoenix.blogApis.Entity.Post;
import com.Phoenix.blogApis.Exceptions.ResourceNotFoundException;
import com.Phoenix.blogApis.Payloads.CommentDto;
import com.Phoenix.blogApis.Repository.CommentRepo;
import com.Phoenix.blogApis.Repository.PostRepo;
import com.Phoenix.blogApis.Service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

	
	
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentdto, Integer postId) {
		log.info("Intiating dao call for save Comments");  
		   Post post = this.postRepo
				.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
		   Comment comment = this.modelMapper.map(commentdto, Comment.class);
		   comment.setPost(post);
		   Comment save = this.commentRepo.save(comment);
		   CommentDto commentDto2 = this.modelMapper
		    		  .map(save, CommentDto.class);
		   log.info("completed dao call for save Comments");   
		return commentDto2;
	}

	@Override
	public void deleteComment(Integer commentId) {
		log.info("Intiating dao call to delete the Comments with:",commentId);
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id",commentId));
		this.commentRepo.delete(comment);
		log.info("complete dao call to delete Comments with:",commentId);
	}

}
