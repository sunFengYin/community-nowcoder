package com.feng.community.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.community.entity.LoginTicket;
import com.feng.community.entity.User;
import com.feng.community.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

public interface UserService extends IService<User> {

    User selectById(@Param("id") int id);

    Map<String, Object> register(User user, String confirmPassword);

    int actication(int userId, String code);

    Map<String, Object> login(String username, String password, int expiredSeconds);

    void logout(String ticket);

    LoginTicket findLoginTicket(String ticket);

    int updateHeader(int userId, String headerUrl);

    User findUserByName(String username);

     Collection<? extends GrantedAuthority> getAuthorities(int userId);
}
