package com.simple.blog.controller;

import com.simple.blog.api.*;
import com.simple.blog.dto.CommentDTO;
import com.simple.blog.dto.Response;
import com.simple.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService service;

    @GetMapping("/{id}")
    public ResponseEntity<CommentInquireResponse> getById(@PathVariable String id){
        CommentDTO commentDTO = service.findCommentById(id);
        return ResponseEntity.ok(new CommentInquireResponse(commentDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<ListOfCommentResponse> getAll(){
        List<CommentDTO> commentDTOList = service.findCommentList();
        return ResponseEntity.ok(new ListOfCommentResponse(commentDTOList));
    }

    @PostMapping("/create")
    public ResponseEntity<CommentAddResponse> createComment(@RequestBody CommentAddRequest commentAddRequest) {
        CommentDTO savedCommentDTO = service.saveComment(commentAddRequest);
        String message = "New comment saved, id = " + savedCommentDTO.getId();
        return ResponseEntity.ok(new CommentAddResponse(savedCommentDTO, message));
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<CommentPublishResponse> publishComment(@PathVariable String id) {
        CommentDTO publishedComment = service.publishComment(id);
        String message = "New comment published, id = " + publishedComment.getId();
        return ResponseEntity.ok(new CommentPublishResponse(publishedComment, message));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Response> likeComment(
            @PathVariable String id,
            @RequestParam String userId) {
        service.likeComment(id, userId);
        String message = String.format("%s liked comment id %s" , userId, id);
        return ResponseEntity.ok(new Response(message));
    }

    @PostMapping("/{id}/unlike")
    public ResponseEntity<Response> unLikeComment(
            @PathVariable String id,
            @RequestParam String userId) {
        service.unLikeComment(id, userId);
        String message = String.format("%s unliked comment id %s" , userId, id);
        return ResponseEntity.ok(new Response(message));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable String id){
        service.deleteCommentById(id);
        String message = "The comment deleted, id = " + id;
        return ResponseEntity.ok(new Response(message));
    }
}
