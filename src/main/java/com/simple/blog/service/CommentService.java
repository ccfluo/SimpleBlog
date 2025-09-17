package com.simple.blog.service;

import com.simple.blog.api.CommentAddRequest;
import com.simple.blog.dto.CommentDTO;
import com.simple.blog.mapping.Comment;

import java.util.List;

public interface CommentService{
    CommentDTO saveComment(CommentAddRequest commentAddRequest);
    CommentDTO publishComment(String id);
    void deleteCommentById(String id);
    List<CommentDTO> findCommentList();
    CommentDTO findCommentById(String id);
}
