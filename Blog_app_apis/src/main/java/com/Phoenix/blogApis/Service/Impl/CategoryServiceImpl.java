package com.Phoenix.blogApis.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Phoenix.blogApis.Entity.Category;
import com.Phoenix.blogApis.Exceptions.ResourceNotFoundException;
import com.Phoenix.blogApis.Payloads.CategoryDTO;
import com.Phoenix.blogApis.Repository.CategoryRepo;
import com.Phoenix.blogApis.Service.CategoryService;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	public CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) 
	{
		log.info("Intiating dao call for save Category Data");
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category savedCategory = categoryRepo.save(category);
		CategoryDTO upatedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
		log.info("completed dao call for save Category Data");
		return upatedCategoryDTO;
	}

	@Override
	public CategoryDTO getCategorybyId(Integer categoryId) 
	{
		log.info("Intiating dao call to get the Category Data with:",categoryId);
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		CategoryDTO dto = this.modelMapper.map(cat, CategoryDTO.class);
		log.info("complete dao call to get Category Data with:",categoryId);
		return dto;
	}

	

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) 
	{
		log.info("Intiating dao call for get Category Data with:",categoryId);
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		cat.setCategoryId(categoryDTO.getCategoryId());
		cat.setCategoryTitle(categoryDTO.getCategoryTitle());
		cat.setCategoryDesc(categoryDTO.getCategoryDesc());
		
		Category category = categoryRepo.save(cat);
		CategoryDTO categoryDTO2 = this.modelMapper.map(category, CategoryDTO.class);
		log.info("complete dao call for get Category Data with:",categoryId);
		
		return categoryDTO2;
	}

	@Override
	public void deleteCategory(Integer categoryId) 
	{	
		log.info("Intiating dao call to delete the Category Data with:",categoryId);
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id", categoryId));
		this.categoryRepo.delete(category);
		log.info("complete dao call to delete Category Data with:",categoryId);
	}

	
	
	
	
	
	@Override
	public List<CategoryDTO> getallCategories() 
	{
		log.info("Intiating dao call for get all Category Data");
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDTO> categoriesDTO = categories.stream().map((category)->this.modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
		log.info("complete dao call for get all Category Data ");
		
		return categoriesDTO;
	}


}
