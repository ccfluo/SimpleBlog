package com.simple.blog.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.simple.blog.dto.CommentDTO;
import com.simple.blog.dto.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonPropertyOrder({"response", "comments"})
@Setter
@Getter
public class CommentAddResponse {
    private Response response;
    private CommentDTO comment;

    public CommentAddResponse(CommentDTO commentDTO){
        this.response = new Response();
        this.comment = commentDTO;
    }

    public CommentAddResponse(CommentDTO commentDTO, String message){
        this.response = new Response(message);
        this.comment = commentDTO;
    }

}