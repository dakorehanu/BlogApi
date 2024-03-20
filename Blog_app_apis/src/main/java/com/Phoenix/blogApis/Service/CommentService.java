package com.Phoenix.blogApis.Service;

import com.Phoenix.blogApis.Payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentdto,Integer postId );

	void deleteComment(Integer commentId);
}
