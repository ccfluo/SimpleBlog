package com.simple.blog.api;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({"comment"})
@Setter
@Getter
public class CommentAddRequest {
    private String content;//吐槽内容
    private String userId;//发布人ID
    private String nickName;//昵称
    private String state;//状态
    private String parentId;//上级ID
    private String articleId;
}