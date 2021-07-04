package com.feng.community;

import com.feng.community.entity.DiscussPost;
import com.feng.community.entity.LoginTicket;
import com.feng.community.mapper.DiscussPostMapper;
import com.feng.community.mapper.LoginTicketMapper;
import com.feng.community.mapper.UserMapper;
import com.feng.community.util.MailClient;
import com.feng.community.util.SensitiveFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class CommunityApplicationTests {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 1,0);
        System.out.println(list);
        System.out.println(discussPostMapper.selectDiscussPostRows(0));

    }

    @Autowired
    private MailClient mailClient;

    @Test
    void testlog() {
        mailClient.sendMail("272395843@qq.com", "test", "welcome.");
    }

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    void test2() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));
        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Autowired
    private SensitiveFilter sensitiveFilter;
    @Test
    void testSensitiveFiler(){
        String text="这里可以赌博，可以嫖娼，可以吸毒，哈哈";
        text=sensitiveFilter.filter(text);
        System.out.println(text);
    }


}
