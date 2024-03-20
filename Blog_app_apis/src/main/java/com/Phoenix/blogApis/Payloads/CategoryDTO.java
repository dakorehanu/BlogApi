package com.Phoenix.blogApis.Payloads;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class CategoryDTO {

	
	private Integer categoryId;
	
	@NotBlank
	@Size(min = 4, message = "Username must be min of 4 character")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=4,message="min size of category desc is 4")
	private String categoryDesc;

	
	
	
}
