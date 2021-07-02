package com.feng.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName comment
 */
@Data
public class Comment implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField("user_id")
    private Integer userId;

    /**
     *
     */
    @TableField("entity_type")
    private Integer entityType;

    /**
     *
     */
    @TableField("entity_id")
    private Integer entityId;

    /**
     *
     */
    @TableField("target_id")
    private Integer targetId;

    /**
     *
     */
    private String content;

    /**
     *0-正常
     */
    private Integer status;

    /**
     *
     */
    @TableField("create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}