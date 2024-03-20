package com.Phoenix.blogApis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Phoenix.blogApis.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
