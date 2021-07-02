package com.feng.community.controller;


import com.feng.community.annotation.LoginRequired;
import com.feng.community.entity.User;
import com.feng.community.service.FollowService;
import com.feng.community.service.LikeService;
import com.feng.community.service.UserService;
import com.feng.community.util.CommunityConstant;
import com.feng.community.util.CommunityUtil;
import com.feng.community.util.HostHolder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController implements CommunityConstant {

    @Value("${community.path.upload}")
    private String uploadPath;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FollowService followService;


    @LoginRequired
    @GetMapping("/setting")
    public String getSettingPage() {
        return "/site/setting";
    }


    //更新头像
    @LoginRequired
    @PostMapping("/upload")
    public String upLoadHeader(MultipartFile headerImage, Model model) {
        //判断
        if (headerImage == null) {
            model.addAttribute("error", "未选择照片");
            return "/site/setting";
        }
        String fileName = headerImage.getOriginalFilename();
        if (fileName.lastIndexOf(".") == -1) {
            model.addAttribute("error", "图片格式不对");
            return "/site/setting";
        }
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!("png".equals(suffix) || "jpg".equals(suffix) || "jpeg".equals(suffix))) {
            model.addAttribute("error", "图片格式仅支持png、jpg、jpeg");
            return "/site/setting";
        }
        //存储
        suffix = fileName.substring(fileName.lastIndexOf("."));
        fileName = CommunityUtil.generateUUID() + suffix;
        File dest = new File(uploadPath + "/" + fileName);
        try {
            headerImage.transferTo(dest);
        } catch (IOException e) {
            log.error("上传文件失败：{}" + e.getMessage());
            throw new RuntimeException("上传文件失败，服务器发生异常!" + e);
        }

        // 更新当前用户的头像的路径(web访问路径)，从hostholder得到当前用户
        // http://localhost:8080/community/user/header/xxx.png
        User user = hostHolder.getUser();
        String headerUrl = domain + contextPath + "/user/header/" + fileName;
        userService.updateHeader(user.getId(), headerUrl);

        return "redirect:/index";
    }

    @GetMapping("/header/{fileName}")
    public void getHeader(@PathVariable("fileName") String fileName,
                          HttpServletResponse response) {
        fileName = uploadPath + "/" + fileName;

        String suffix = fileName.substring(fileName.lastIndexOf("."));
        response.setContentType("image/" + suffix);
        try (
                FileInputStream fis = new FileInputStream(fileName);
                OutputStream os = response.getOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fis.read(buffer)) != -1) {
                os.write(buffer, 0, b);
            }
        } catch (IOException e) {
            log.error("读取头像失败: " + e.getMessage());
        }

    }

    //个人主页
    @GetMapping("/profile/{userId}")
    public String getProfiePage(@PathVariable("userId") int userId,
                                Model model) {
        User user = userService.selectById(userId);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        //用户
        model.addAttribute("user", user);
        //点赞的数量
        int likeCount = likeService.findUserLikeCount(userId);
        model.addAttribute("likeCount", likeCount);

        //关注数量
        long followeeCount = followService.findFolloweeCount(userId, ENTITY_TYPE_USER);
        model.addAttribute("followeeCount", followeeCount);
        //粉丝数量
        long followerCount = followService.findFollowerCount(ENTITY_TYPE_USER, userId);
        model.addAttribute("followerCount", followerCount);
        //是否已关注
        boolean hasFollowed = false;
        if (hostHolder.getUser() != null) {
            hasFollowed = followService.hasFollowed(hostHolder.getUser().getId(), ENTITY_TYPE_USER, userId);
        }
        model.addAttribute("hasFollowed", hasFollowed);


        return "/site/profile";
    }

}
























