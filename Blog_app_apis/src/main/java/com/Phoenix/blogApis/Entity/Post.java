package com.Phoenix.blogApis.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
@Entity
@Table(name="post")
public class Post {

	
	@Id
	private Integer postId;
	
	@Column(name="post_title", length=100, nullable=false)
	private String postTitle;
	
	@Column(name="post_content",length=10000)
	private String postContent;
	
	private String imageName;
	
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn (name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(mappedBy="post",cascade =CascadeType.ALL )
	private Set<Comment> comments=new HashSet<>(); 
	
	
	
}
