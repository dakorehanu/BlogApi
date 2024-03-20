package com.Phoenix.blogApis.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Phoenix.blogApis.Api_constants.ApiConstants;
import com.Phoenix.blogApis.Payloads.ApiResponse;
import com.Phoenix.blogApis.Payloads.CategoryDTO;
import com.Phoenix.blogApis.Service.Impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api")
public class CategoryController {

	@Autowired
	public CategoryServiceImpl categoryServiceImpl;
	
	
	Logger logger=LoggerFactory.getLogger(CategoryController.class);
	
	
	
	/**@author Raviraj Patil
	 * @apiNote createCategory
	 * @since 1.0
	 * @param categoryDTO
	 * @return CategoryDTO
	 */
	@PostMapping("/category")
	public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO)
	{
		logger.info("Entering the request for save Category Data");
		CategoryDTO createCategory = categoryServiceImpl.createCategory(categoryDTO);
		logger.info("Completing the request for save Category Data");
		
		return new ResponseEntity<CategoryDTO>(createCategory,HttpStatus.CREATED);
	}
	
	
	
	/**@author Raviraj Patil
	 * @apiNote Update Category
	 * @param categoryDTO
	 * @param CategoryId
	 * @return CategoryDTo
	 */
	@PutMapping("/category{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory
	(@RequestBody CategoryDTO categoryDTO,
	 @PathVariable Integer categoryId)
	{
		logger.info("Entering the request to update  Category Data with {}:",categoryId);
		CategoryDTO updateCategory = categoryServiceImpl.updateCategory(categoryDTO, categoryId);
		logger.info("Completed the request to update Category Data with {}:",categoryId);
		
		return new ResponseEntity<CategoryDTO>(updateCategory,HttpStatus.CREATED);
	}
	
	
	
	/**@author Raviraj Patil
	 * @apiNote Get category By id
	 * @since 1.0 
	 * @param categoryId
	 * @return  CategoryDTO
	 */
	@GetMapping("/category{categoryId}")
	public ResponseEntity<CategoryDTO> getCastegoryById(@PathVariable Integer categoryId)
	{
		logger.info("Entering the request for get  Category Data with {}:", categoryId);
		CategoryDTO categorybyId = categoryServiceImpl.getCategorybyId(categoryId);
		logger.info("Completed the request for get Category Data with {}:", categoryId);
		
		return new ResponseEntity<CategoryDTO>(categorybyId,HttpStatus.FOUND);
	}
	
	

	/**@author Raviraj Patil
	 * @apiNote Get all categories
	 * @return List<VCategoryDTO>
	 */
	@GetMapping("/categories")
	public ResponseEntity<List<CategoryDTO>> getAllUsers(){
		logger.info("Entering the request for get all Category Data");
		 List<CategoryDTO> getallCategories = this.categoryServiceImpl.getallCategories();
		logger.info("Completed the request for get All Category Data");
		return new ResponseEntity<List<CategoryDTO>>(getallCategories,HttpStatus.FOUND);	
	}
	
	
	
	
	/**@author Raviraj Patil
	 *@apiNote delete Category 
	 * @since 1.0
	 * @param categoryId
	 * @return ApiResponse
	 */
	@DeleteMapping("/category{categoryId}")
	public ResponseEntity<ApiResponse>  deleteCategory(@PathVariable Integer categoryId)
	{
		logger.info("Entering the request to delete  Category Data with {}:",categoryId);
		this.categoryServiceImpl.deleteCategory(categoryId);
		logger.info("Completed the request to delete Category Data with {}:",categoryId );
		
		return new ResponseEntity<ApiResponse>(new ApiResponse(ApiConstants.DELETE_CATEGORY, true),HttpStatus.OK);
		
	}
	
	
	
}
