package com.simple.blog.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.simple.blog.dto.CommentDTO;
import com.simple.blog.dto.Response;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"response", "comment"})
@Setter
@Getter
public class CommentInquireResponse {
    private Response response;
    private CommentDTO comment;

    public CommentInquireResponse(CommentDTO commentDTO){
        this.response = new Response();
        this.comment = commentDTO;
    }

}