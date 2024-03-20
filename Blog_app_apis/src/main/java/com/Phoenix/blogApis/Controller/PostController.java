package com.Phoenix.blogApis.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.Phoenix.blogApis.Api_constants.ApiConstants;
import com.Phoenix.blogApis.Payloads.ApiResponse;
import com.Phoenix.blogApis.Payloads.PostDto;
import com.Phoenix.blogApis.Payloads.PostResponse;
import com.Phoenix.blogApis.Service.FileService;
import com.Phoenix.blogApis.Service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	Logger logger=LoggerFactory.getLogger(PostController.class);
	
	
	
	/**
	 * @author Raviraj Patil
	 * @apiNote Create Posts
	 * @since 1.0
	 * @param PostDto
	 * @return PostDto
	 */
	@PostMapping("user{userId}/category{categoryId}/posts")
	public ResponseEntity<PostDto> createPost
		   (@RequestBody PostDto postdto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)	
	{
		logger.info("Entering the request for save post Data");
		PostDto createPost = this.postService.createPost(postdto, userId, categoryId);
		logger.info("Completing the request for save Post Data");
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	
	
	
	/**@author Raviraj Patil
	 * @apiNote updatePost
	 * @param postId
	 * @return PostDto
	 */
	@PutMapping("/posts{postId}")
	public ResponseEntity<PostDto> updatePost
		   (@RequestBody PostDto postdto,
			@PathVariable Integer postId)
	{
		logger.info("Entering the request to update  post Data with {}:",postId);
		PostDto updatePost = this.postService.updatePost(postdto, postId);
		logger.info("Completed the request to update Posts Data with {}:",postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);	
	}
	
	
	
	
	
	/**@author Raviraj Patil
	 * @apiNote getPostById
	 * @since 1.0
	 * @param PostId
	 * @return PostDto
	 */
	@GetMapping("/posts{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		logger.info("Entering the request for get  post Data with {}:", postId);
		 PostDto dto = this.postService.getPostById(postId);
		logger.info("Completed the request for get Posts Data with {}:", postId);
		
		return new ResponseEntity<PostDto>(dto,HttpStatus.FOUND);
	}
	
	
	
	
	/**@author Raviraj Patil
	 * @apiNote GetPostByUser
	 * @param userId
	 * @since 1.0
	 * @return PostDto
	 */
	@GetMapping("/user{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId)
	{
		logger.info("Entering the request for get the post Data with{}: ",userId);
		List<PostDto> posts=this.postService.getPostByUser(userId);
		logger.info("Completed the request for get Post Data with {}: ",userId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.FOUND);
	}
	
	
	
	
	/**@author Raviraj Patil
	 * @apiNote getPostsByCat
	 * @param categoryId
	 * @since 1.0
	 * @return List<PostDto>
	 */
	@GetMapping("/category{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCat(@PathVariable Integer categoryId ){
		logger.info("Entering the request for get the post Data with {}:", categoryId);
		List<PostDto> posts=this.postService.getPostByCategory(categoryId);
		logger.info("Completed the request for get Post Data with {}:",categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.FOUND);
	}
	
	
	
	
	/**@author Raviraj Patil
	 * @apiNote getallPosts
	 * @since 1.0
	 * @return List<PostDto>
	 */
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam (value="pageNumber",defaultValue=ApiConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=ApiConstants.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=ApiConstants.SORT_BY,required = false) String sortBy, 
			@RequestParam(value="sortDir",defaultValue=ApiConstants.SORT_DIR,required =false ) String sortDir
			)
	{
		logger.info("Entering the request for get all posts Data");
		 PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		logger.info("Complete the request for get All Posts Data");
		
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.FOUND);
	}
	
	
	
	
	/**@author Raviraj Patil
	 * @apiNote deletePost
	 * @param postId
	 * @return ApiResponse
	 */
	@DeleteMapping("/posts{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
		logger.info("Entering the request to delete  post Data with {}:",postId);
		this.postService.deletePost(postId);
		logger.info("Completed the request to delete Posts Data with {}:",postId );
		return new ResponseEntity<ApiResponse>(new ApiResponse("ApiConstants.DELETE_POST, true),HttpStatus.OK);                                                                                  ",true),HttpStatus.OK);
	}
	
	
	
	
	/**@author Raviraj Patil
	 * @apiNote UploadPostImage
	 * @param image
	 * @param postId
	 * @return PostDto
	 * @throws IOException
	 */
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam ("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException{
		logger.info("Entering the request to upload  postImage Data with {}:",postId);
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
	  
	   postDto.setImageName(fileName);
	   PostDto updatePost = this.postService.updatePost(postDto, postId);
	   logger.info("Completed the request for upload PostImage Data with {}:",postId);
	return new ResponseEntity<PostDto>(updatePost,HttpStatus.FOUND);
	}
	
	
	/**@author Raviraj Patil
	 * @apiNote download postImage
	 * @param imageName
	 * @param response
	 * @throws IOException
	 */
	@GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,HttpServletResponse response
			) throws IOException {
		logger.info("Entering the request to download  postImage with{}:",imageName);
		InputStream resource =this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
		logger.info("Completed the request for download PostImage Data with {}:",imageName);
	}
	
	/**
	 * @author shubham pawar
	 * @apiNote  Pathch User
	 * @implNote
	 */
	@GetMapping("/message")
	public ResponseEntity<String> getMessage()
{
		String s="Hanuman";
	return new ResponseEntity<String>(s,HttpStatus.FOUND);
		
	}
	
	
	
}
