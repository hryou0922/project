package com.hry.project.dictation.controller;


import com.hry.project.dictation.dto.UserInfoRsp;
import com.hry.project.dictation.dto.UserReq;
import com.hry.project.dictation.dto.UserLoginDto;
import com.hry.project.dictation.dto.page.CommonEntity;
import com.hry.project.dictation.service.IWordService;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hry
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/user")
public class UserCtl {
    private static final Logger logger = LoggerFactory.getLogger(UserCtl.class);

    @Autowired
    private IWordService wordService;

    /**
     * 登录
     * @param userDto
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public CommonEntity<UserLoginDto> login(@RequestBody UserReq userDto){
        logger.info("收到登录请求:{}", CommonJsonUtils.toJsonString(userDto));
        CommonEntity<UserLoginDto> commonEntity = new CommonEntity<>();
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setToken(System.currentTimeMillis() + "");
        commonEntity.setData(userLoginDto);
        return commonEntity;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public CommonEntity<UserInfoRsp> info(@RequestParam String token){
        logger.info("收到请求用户信息:{}", token);

        Map<String, Object>  userMap = new HashMap<>();
        String[] arrays = new String[1];
        arrays[0] = "admin";

        CommonEntity<UserInfoRsp> commonEntity = new CommonEntity<>();
        UserInfoRsp userInfoRsp = new UserInfoRsp();
        userInfoRsp.setRoles(arrays);
        userInfoRsp.setIntroduction("I am a super administrator");
        userInfoRsp.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfoRsp.setName("Super Admin");

        commonEntity.setData(userInfoRsp);
        return commonEntity;
    }

    /**
     * 收到退出请求
     * @param token
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public CommonEntity<String> logout(@RequestParam(required = false) String token){
        logger.info("TODO 收到退出请求:{}", token);

        CommonEntity<String> commonEntity = new CommonEntity<>();
        commonEntity.setData("success");
        return commonEntity;
    }

}

