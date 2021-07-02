package com.feng.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.community.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> selectCommentsByEntity(@Param("entityType")int entityType, @Param("entityId")int entityId, @Param("offset")int offset, @Param("limit")int limit);

    int selectCountByEntity(@Param("entityType")int entityType, @Param("entityId")int entityId);

    int insertComment(Comment comment);

    Comment selectCommentById(@Param("id")int id);

    int selectCommentCountById(@Param("id")int id);

    List<Comment> selectCommentsByUserId(@Param("id")int id, @Param("offset")int offset, @Param("limit")int limit);

    int updateStatus(@Param("entityId")int entityId, @Param("status")int status);

}
