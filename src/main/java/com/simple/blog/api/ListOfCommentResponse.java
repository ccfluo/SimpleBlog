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
public class ListOfCommentResponse {
    private Response response;
    private List<CommentDTO> comments;

    public ListOfCommentResponse(List<CommentDTO> commentDTOList){
        this.response = new Response();
        this.comments = commentDTOList;
    }

}