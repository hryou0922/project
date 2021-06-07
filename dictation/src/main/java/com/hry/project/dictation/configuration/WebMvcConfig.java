package com.hry.project.dictation.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/7 11:39
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     *  允许跨域访问
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 可限制哪个请求可以通过跨域
                .allowedHeaders("*")  // 可限制固定请求头可以通过跨域
                .allowedMethods("*") // 可限制固定methods可以通过跨域
                .allowedOrigins("*")  // 可限制访问ip可以通过跨域
                // .allowCredentials(true) // 是否允许发送cookie
                .exposedHeaders(HttpHeaders.SET_COOKIE);
    }

}
