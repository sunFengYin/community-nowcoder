package com.feng.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

/**
 * 存两类
 * 第一：存user->user
 * 第二：存系统->user
 * @TableName message
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    @TableField("from_id")
    private Integer fromId;

    /**
     *
     */
    @TableField("to_id")
    private Integer toId;

    /**
     *拼接：form+to
     * 规则：小的放前面
     */
    @TableField("conversation_id")
    private String conversationId;

    /**
     *
     */
    private String content;

    /**
     * 0-未读;1-已读;2-删除;
     */
    private Integer status;

    /**
     *
     */
    @TableField("create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

}