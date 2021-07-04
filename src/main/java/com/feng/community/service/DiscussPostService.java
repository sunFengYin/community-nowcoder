package com.feng.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscussPostService extends IService<DiscussPost> {


    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit,int orderMode);

    int selectDiscussPostRows(@Param("userId") int userId);

    int addDiscussPost(DiscussPost post);

    DiscussPost findDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);

     int updateType(int id, int type);

     int updateStatus(int id, int status);

     int updateScore(int id, double score);
}
