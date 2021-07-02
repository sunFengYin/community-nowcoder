package com.feng.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName discuss_post
 */

@Document(indexName = "discusspost", shards = 6, replicas = 3)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("discuss_post")
public class DiscussPost implements Serializable {
    /**
     *
     */

    @Id
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @Field(type = FieldType.Integer)
    @TableField("user_id")
    private Integer userId;

    /**
     *
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String title;

    /**
     *
     */
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String content;

    /**
     * 0-普通; 1-置顶;
     */
    @Field(type = FieldType.Integer)
    private Integer type;

    /**
     * 0-正常; 1-精华; 2-拉黑;
     */
    @Field(type = FieldType.Integer)
    private Integer status;

    /**
     *ES的转换很关键！！！！！！
     */
    @Field(type = FieldType.Date ,format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 表示当前的帖子有多少评论
     */
    @Field(type = FieldType.Integer)
    @TableField("comment_count")
    private Integer commentCount;

    /**
     *
     */
    @Field(type = FieldType.Double)
    private Double score;

    private static final long serialVersionUID = 1L;

}