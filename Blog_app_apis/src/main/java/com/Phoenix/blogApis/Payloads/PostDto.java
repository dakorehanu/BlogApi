package com.Phoenix.blogApis.Payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class PostDto {

	private Integer postId;
	
	@NotEmpty
	@Size(min = 4, message = "postTitle must be min of 10 character")
	private String postTitle;
	
	@NotBlank
	@Size(min=4,message="PostContent must be min of 10 character ")
	private String postContent;
	
	private String imageName;
	
	private Date addedDate;
	
	private UserDTO user;
	
	private CategoryDTO category;

	private Set<CommentDto> comments=new HashSet<>();
	
}
