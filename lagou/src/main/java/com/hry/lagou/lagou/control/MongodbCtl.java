package com.hry.lagou.lagou.control;

import com.hry.lagou.lagou.mongodb.MongoTemplateMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Deprecated
public class MongodbCtl {

    @Autowired
    private MongoTemplateMsg mongoTemplateMsg;

    @RequestMapping("/db")
    public String first() {
        mongoTemplateMsg.save();
        return "first/index";
    }


}
