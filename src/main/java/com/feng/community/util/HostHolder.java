package com.feng.community.util;


import com.feng.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 用于代替session对象
 */
@Component
public class HostHolder {
    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user) {
        users.set(user);
    }

    public User getUser() {
        return users.get();
    }

    public void clear() {
        users.remove();
    }
}
