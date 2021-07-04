package com.feng.community.quartz;

import com.feng.community.entity.DiscussPost;
import com.feng.community.service.DiscussPostService;
import com.feng.community.service.ElasticsearchService;
import com.feng.community.service.LikeService;
import com.feng.community.util.CommunityConstant;
import com.feng.community.util.RedisKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class PostScoreRefreshJob implements Job, CommunityConstant {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private LikeService likeService;

    // 牛客纪元
    private static final Date epoch;

    @Autowired
    private ElasticsearchService elasticsearchService;

    static {
        try {
            epoch = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-08-01 00:00:00");
        } catch (ParseException e) {
            throw new RuntimeException("初始化牛客纪元失败！", e);
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String redisKey = RedisKeyUtil.getPostScoreKey();
        BoundSetOperations operations = redisTemplate.boundSetOps(redisKey);

        if(operations.size() == 0){
            log.info("[任务取消] 没有需要刷新的帖子！");
            return;
        }
        log.info("[任务开始] 正在刷新帖子分数：" + operations.size());
        while(operations.size() > 0){
            refresh((int)operations.pop());
        }
        log.info("[任务结束]帖子分数刷新完毕！");
    }

    // 更新一个帖子的分数
    private void refresh(int postId){
        DiscussPost post = discussPostService.findDiscussPostById(postId);

        if(post == null){
            log.error("该帖子不存在：id = " + postId);
            return;
        }

        // 是否精华
        boolean wonderful = post.getStatus() == 1;
        // 评论数量
        int commentCount = post.getCommentCount();
        // 点赞数量
        int likeCount = (int) likeService.findEntityLikeCount(ENTITY_TYPE_POST, postId);
        // 计算分数
        double temp = (wonderful ? 75 : 0) + commentCount * 10 + likeCount * 2;
        double score = Math.log10(Math.max(temp, 1))
                + (post.getCreateTime().getTime() - epoch.getTime()) / (1000 * 3600 * 24);

        // 更新帖子分数
        discussPostService.updateScore(postId, score);
        // 同步数据到ElasticSearch
        post.setScore(score);
        elasticsearchService.saveDiscussPost(post);
    }
}
