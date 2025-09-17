package com.simple.blog.converter;

import com.simple.blog.dto.CommentDTO;
import com.simple.blog.mapping.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


//@Component 后，Spring 会自动创建它的单例实例，
// 无需手动通过 new CustomerConverter() 创建对象，
// 实现了 “对象创建权由 Spring 接管”
// 告诉 Spring“这个类需要被管理，请创建它的实例并放到容器里”
@Component
public class CommentConverter {
    public CommentDTO commentToDto(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        BeanUtils.copyProperties(comment,commentDTO);
        return commentDTO;
    }

    public Comment commentDTOTo(CommentDTO commentDTO){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO, comment);
        return comment;
    }

}
