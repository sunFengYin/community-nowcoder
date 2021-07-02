package com.feng.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.community.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {

     List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit);

     int findCommentCount(int entityType, int entityId);

     int addComment(Comment comment);

     Comment findCommentById(int id);
}
