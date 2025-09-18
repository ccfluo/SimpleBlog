package com.simple.blog.service;

import com.mongodb.client.result.UpdateResult;
import com.simple.blog.api.CommentAddRequest;
import com.simple.blog.converter.CommentConverter;
import com.simple.blog.dto.CommentDTO;
import com.simple.blog.exception.BusinessException;
import com.simple.blog.po.Comment;
import com.simple.blog.respository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

//评论的业务层
@Slf4j
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private CommentConverter commentConverter;
    /**
     * 保存一个评论
     * @param commentAddRequest
     */
    public CommentDTO saveComment(CommentAddRequest commentAddRequest){
//如果需要自定义主键，可以在这里指定主键；如果不指定主键，MongoDB会自动生成主键
//设置一些默认初始值。。。
        Comment comment = new Comment();
        comment.setContent(commentAddRequest.getContent());
        comment.setUserid(commentAddRequest.getUserid());
        comment.setNickname(commentAddRequest.getNickname());
        comment.setCreatetime(LocalDateTime.now());
        comment.setState("saved");
        comment.setLikecount(0);
        comment.setReplycount(0);
        comment.setParentid(commentAddRequest.getParentid());
        comment.setArticleid(commentAddRequest.getArticleid());

        Comment savedComment = commentRepository.save(comment);
        return commentConverter.commentToDto(savedComment);
    }
    /**
     * 更新评论
     * @param id
     */
    public CommentDTO publishComment(String id){
        CommentDTO commentDTO = findCommentById(id);
        if (commentDTO.getState().equals("saved")) {
            Comment comment = commentConverter.commentDTOTo(commentDTO);
            comment.setState("published");
            comment.setPublishtime(LocalDateTime.now());
            Comment publishedComment = commentRepository.save(comment);
            return commentConverter.commentToDto(publishedComment);
        } else {
            throw new BusinessException("comment status is invalid");
        }

    }
    /**
     * 根据id删除评论
     * @param id
     */
    public void deleteCommentById(String id){
        CommentDTO commentDTO = findCommentById(id);
        commentRepository.deleteById(id);
    }
    /**
     * 查询所有评论
     * @return
     */
    public List<CommentDTO> findCommentList(){
        List<Comment> commentList= commentRepository.findAll();
        List<CommentDTO> commentDTOList = commentList.stream().map(commentConverter::commentToDto).collect(Collectors.toList());
        return commentDTOList;
    }
    /**
     * 根据id查询评论
     * @param id
     * @return
     */
    public CommentDTO findCommentById(String id){
        Optional<Comment> commentOpt = commentRepository.findById(id);
        if (commentOpt.isPresent()) {
            Comment comment = commentOpt.get();
            return (commentConverter.commentToDto(comment));
        } else {
            throw new NoSuchElementException("No such comment found");
        }
    }

    @Override
    public void likeComment(String id, String userId) {
        // id = input inputted id and likedUserIds doesn't contain inputted userId
//        Query query = Query.query(Criteria.where("_id").is(id)).
//                addCriteria(Criteria.where("likedUserIds").ne(userId));
        Query query = Query.query(
                Criteria.where("_id").is(id)
                        .and("likedUserIds").ne(userId)
        );
        Update update = new Update();
        update.inc("likecount");
        update.addToSet("likedUserIds", userId);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "comment");
        if (updateResult.getModifiedCount() > 0) {
            return;
        } else if (updateResult.getMatchedCount() > 0) {
            throw new BusinessException("BAD_REQUEST", "system error, pls check backend");
        } else {
            throw new BusinessException("BAD_REQUEST", "user has already liked comment");
        }
    }

    @Override
    public void unLikeComment(String id, String userId) {
        Query query = Query.query(
                Criteria.where("_id").is(id)
                        .and("likedUserIds").is(userId)
        );

        Update update = new Update();
        update.inc("likecount", -1);
        update.pull("likedUserIds", userId);

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, "comment");
        if (updateResult.getModifiedCount() > 0) {
            return;
        } else if (updateResult.getMatchedCount() > 0) {
            throw new BusinessException("BAD_REQUEST", "system error, pls check backend");
        } else {
            throw new BusinessException("BAD_REQUEST", "user has not liked comment id");
        }
    }

}