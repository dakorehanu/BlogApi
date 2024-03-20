package com.Phoenix.blogApis.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
@Table(name="comments")
public class Comment {

	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer CommentId;
	
	private String content;
	
	@ManyToOne
	private Post post;
}
