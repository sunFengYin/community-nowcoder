package com.feng.community.service;

import com.feng.community.entity.DiscussPost;

import java.util.List;

public interface ElasticsearchService {

     void saveDiscussPost(DiscussPost post);
     void deleteDiscussPost(int id);
     List<Object> searchDiscussPost(String keyword, int current, int limit);
}
