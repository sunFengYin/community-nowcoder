package com.feng.community;

import com.feng.community.elasticsearch.DiscussPostRepository;
import com.feng.community.entity.DiscussPost;
import com.feng.community.mapper.DiscussPostMapper;
import lombok.AllArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;


/**
 * ElasticSearch整合
 *
 * 第一步：导依赖
 * 第二步：创建对应的实体类并添加注解
 * 第三步：配置elasticsearch 的Repository！
 */

@SpringBootTest
public class ElasticsearchTest {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private DiscussPostRepository discussPostRepository;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void testInsert(){
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(5));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(6));
        discussPostRepository.save(discussPostMapper.selectDiscussPostById(7));
    }

    @Test
    public void testupdate(){
        DiscussPost post = discussPostMapper.selectDiscussPostById(2);
        post.setContent("elasticsearch is done!!!!!");
        discussPostRepository.save(post);
    }

    @Test
    public void testDelete(){
        discussPostRepository.existsById(2);
    }

    @Test
    public void testSearch(){

//        DiscussPost post = discussPostRepository.findById(3).get();
//        System.out.println(post);
        Query searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.multiMatchQuery("互联网寒冬", "title", "content"))
                .withSort(SortBuilders.fieldSort("type").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("score").order(SortOrder.DESC))
                .withSort(SortBuilders.fieldSort("createTime").order(SortOrder.DESC))
                .withPageable(PageRequest.of(0, 10))
                .withHighlightFields(
                        new HighlightBuilder.Field("title").preTags("<em>").postTags("</em>"),
                        new HighlightBuilder.Field("content").preTags("<em>").postTags("</em>")
                ).build();
        elasticsearchRestTemplate.queryForObject((GetQuery) searchQuery,DiscussPost.class);
    }
}
