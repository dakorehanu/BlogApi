package com.Phoenix.blogApis.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Phoenix.blogApis.Api_constants.ApiConstants;
import com.Phoenix.blogApis.Payloads.ApiResponse;
import com.Phoenix.blogApis.Payloads.CommentDto;
import com.Phoenix.blogApis.Service.Impl.CommentServiceImpl;

@RestController
@RequestMapping("/api")
public class CommentController {

	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	Logger logger=LoggerFactory.getLogger(CommentController.class);
	
	/**@author Raviraj Patil
	 * @apiNote createComment
	 * @param commentdto
	 * @param postId
	 * @
	 * @return  CommentDto
	 */
	@PostMapping("/post{postId}/comments")
	public ResponseEntity<CommentDto> createComment
	(@RequestBody CommentDto commentdto,@PathVariable Integer postId){
		logger.info("Entering the request for save Comments");
		CommentDto comment = this.commentServiceImpl.createComment(commentdto, postId);
		logger.info("Completing the request for save Comments");
		return new ResponseEntity<CommentDto>(comment,HttpStatus.FOUND);
		
		
	}
	
	
	/**@author Raviraj Patil
	 * @apiNote deleteComment
	 * @param commentId
	 * @
	 * @return ApiResponse
	 */
	@DeleteMapping("/comments{commentId}")
	 public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		logger.info("Entering the request to delete  comments  with {}:",commentId);
		this.commentServiceImpl.deleteComment(commentId);
		logger.info("Completed the request to delete Comments with {}:",commentId );
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstants.DELETE_COMMENT, true),HttpStatus.OK);
	 }
}
