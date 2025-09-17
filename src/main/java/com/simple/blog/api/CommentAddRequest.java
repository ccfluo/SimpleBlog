package com.simple.blog.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.simple.blog.dto.CommentDTO;
import com.simple.blog.dto.Response;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@JsonPropertyOrder({"comment"})
@Setter
@Getter
public class CommentAddRequest {
    private String content;//吐槽内容
    private String userid;//发布人ID
    private String nickname;//昵称
    private String state;//状态
    private String parentid;//上级ID
    private String articleid;
}