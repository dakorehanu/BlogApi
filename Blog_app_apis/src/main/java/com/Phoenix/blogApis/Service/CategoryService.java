package com.Phoenix.blogApis.Service;

import java.util.List;

import com.Phoenix.blogApis.Payloads.CategoryDTO;

public interface CategoryService {

	
	//create
	
	 CategoryDTO createCategory(CategoryDTO categoryDTO);
	
	//get
	
	 CategoryDTO getCategorybyId(Integer CategoryId);
	
	//getAll
	
	 List<CategoryDTO> getallCategories();
	
	
	//update
	
	 CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer CategoryId);
	
	
	//delete 
	
	 void deleteCategory(Integer CategoryId);
	
	
	
}
