package com.Phoenix.blogApis.Payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommentDto {

	
	
	private Integer commentId;
	
	@NotEmpty
	@Size(min = 10, message = "postTitle must be min of 10 character")
	private String content;
}
