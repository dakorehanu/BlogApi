package com.Phoenix.blogApis.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{


	

	String resourcename;
	
	String fieldname;
	
	long fieldvalue;
}
