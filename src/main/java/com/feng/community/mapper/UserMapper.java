package com.feng.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.community.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {


    User selectById(@Param("id")int id);

    User selectByName(@Param("username")String username);

    User selectByEmail(@Param("email")String email);

    int insertUser(User user);

    int updateStatus(@Param("id")int id, @Param("status")int status);

    int updateHeader(@Param("id")int id, @Param("headerUrl")String headerUrl);

    int updatePassword(@Param("id")int id,@Param("password") String password);

}
