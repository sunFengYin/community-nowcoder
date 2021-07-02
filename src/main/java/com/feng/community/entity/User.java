package com.feng.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName user
 */
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 随机字符串，用来后面组合加密密码
     */
    private String salt;

    /**
     * 
     */
    private String email;

    /**
     * 0-普通用户; 1-版主; 2-管理员;
     */
    private Integer type;

    /**
     * 0-未激活; 1-已激活;
     */
    private Integer status;

    /**
     * 
     */
    @TableField("activation_code")
    private String activationCode;

    /**
     * 
     */
    @TableField("header_url")
    private String headerUrl;

    /**
     * 
     */
    @TableField("create_time")
    private Date createTime;

    private static final long serialVersionUID = 1L;

}