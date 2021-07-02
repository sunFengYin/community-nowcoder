package com.feng.community.service;

public interface LikeService {

    void like(int userId,int entityType,int entityId,int entityUserId);

    long findEntityLikeCount(int entityType,int entityId);

    int findEntityLikeStatus(int userid,int entityType,int entityId);

     int findUserLikeCount(int userId);
}
