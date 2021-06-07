package com.hry.project.dictation.controller;


import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.WordQry;
import com.hry.project.dictation.model.WordModel;
import com.hry.project.dictation.service.WordService;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hry
 * @since 2021-06-04
 */
@RestController
@RequestMapping("/word")
public class WordCtl {
    private static final Logger logger = LoggerFactory.getLogger(WordCtl.class);

    @Autowired
    private WordService wordService;

    @RequestMapping(value = "list")
    public MyPage<WordModel> list(@ModelAttribute WordQry qry){
        logger.info("收到登录请求:{}", CommonJsonUtils.toJsonString(qry));
        return wordService.queryPage(qry);
    }

}

