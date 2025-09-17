package com.simple.blog.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {
    private String id;//主键
    private String content;//吐槽内容
    private String userid;//发布人ID
    private String nickname;//昵称
    private Integer likenum;//点赞数
    private Integer replynum;//回复数
    private String state;//状态
    private String parentid;//上级ID
    private String articleid;
    private LocalDateTime createtime;//评论的日期时间
    private LocalDateTime publishtime;//发布日期
}