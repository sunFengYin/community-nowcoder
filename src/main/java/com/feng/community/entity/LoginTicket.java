package com.feng.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName login_ticket
 */


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@TableName("login_ticket")
public class LoginTicket implements Serializable {
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
    private String ticket;

    /**
     * 0-有效; 1-无效;
     */
    private Integer status;

    /**
     * 
     */
    private Date expired;

    private static final long serialVersionUID = 1L;

}