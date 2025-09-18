package com.simple.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private String id;//主键
    private String content;//吐槽内容
    private String userId;//发布人ID
    private String nickName;//昵称
    private Integer likeCount;//点赞数
    private Integer replyCount;//回复数
    private String state;//状态
    private String parentid;//上级ID
    private String articleId;
    private LocalDateTime createTime;//评论的日期时间
    private LocalDateTime publishTime;//发布日期
}