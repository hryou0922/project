package com.hry.project.dictation.dto;

/**
 * 登录用户
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/7 11:27
 */
public class UserReq {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
