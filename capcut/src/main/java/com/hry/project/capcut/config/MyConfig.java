package com.hry.project.capcut.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/12 23:30
 */
@ConfigurationProperties(prefix = "my.config")
@Configuration
@Data
public class MyConfig {
    /**
     * map目录
     */
    private String mp3Dir;
    /**
     * pic 目录
     */
    private String picDir;
    /**
     * draft_content 模板目录
     */
    private String parentContentDir;
    /**
     * draft_content 目标目录
     */
    private String sonContentDir;

    private String configDir;
}
