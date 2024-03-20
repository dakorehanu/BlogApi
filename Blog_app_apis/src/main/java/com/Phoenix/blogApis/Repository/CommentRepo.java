package com.Phoenix.blogApis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Phoenix.blogApis.Entity.Comment;

public interface CommentRepo  extends JpaRepository<Comment, Integer>{

}
