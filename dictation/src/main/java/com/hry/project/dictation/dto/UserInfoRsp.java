package com.hry.project.dictation.dto;

/**
 * 用户返回的信息
 *
 * {
 *     roles: ['admin'],
 *     introduction: 'I am a super administrator',
 *     avatar: 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
 *     name: 'Super Admin'
 *   }
 *
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/7 14:04
 */
public class UserInfoRsp {
    private String[] roles;
    private String introduction;
    private String avatar;
    private String name;

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
