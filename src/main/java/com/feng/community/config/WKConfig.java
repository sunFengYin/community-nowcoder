package com.feng.community.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

//@Configuration
@Slf4j
public class WKConfig {

    @Value("${wk.image.storage}")
    private String wkImageStorage;

    @PostConstruct           //这个注解表示启动服务的时候，init()方法会调用一次
    public void init() {
        // 创建WK图片目录
        File file = new File(wkImageStorage);
        if (!file.exists()) {
            file.mkdir();
            log.info("创建WK图片目录: " + wkImageStorage);
        }
    }
}
