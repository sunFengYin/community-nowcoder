package com.feng.community.controller;

import com.feng.community.entity.DiscussPost;
import com.feng.community.entity.Page;
import com.feng.community.entity.User;
import com.feng.community.service.LikeService;
import com.feng.community.service.UserService;
import com.feng.community.service.impl.DiscussPostService;
import com.feng.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements CommunityConstant {

    @Autowired
    private UserService userService;

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private LikeService likeService;

    @GetMapping("/index")
    public String getIndex(Model model, Page page,
                           @RequestParam(name = "orderMode",defaultValue = "0") int orderMode) {

        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/index?orderMode="+orderMode);
        List<DiscussPost> lists = discussPostService.selectDiscussPosts(0, page.getOffset(), page.getLimit(),orderMode);
        ArrayList<Map<String, Object>> discussPosts = new ArrayList<>();
        if (!lists.isEmpty()) {
            for (DiscussPost post : lists) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.selectById(post.getUserId());
                map.put("user", user);
                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
                map.put("likeCount",likeCount);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("orderMode",orderMode);
        return "/index";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "/error/500";
    }

    //权限不足访问的页面
    @GetMapping("/denied")
    public String getDeniedPage(){
        return "/error/404";
    }
}
