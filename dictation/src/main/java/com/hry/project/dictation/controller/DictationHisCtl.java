package com.hry.project.dictation.controller;


import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.DictationHisTmpQry;
import com.hry.project.dictation.model.DictationHisModel;
import com.hry.project.dictation.service.DictationHisService;
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
 * @since 2021-06-08
 */
@RestController
@RequestMapping("/dictation-his")
public class DictationHisCtl {
    private static final Logger logger = LoggerFactory.getLogger(DictationHisTmpCtl.class);

    @Autowired
    private DictationHisService dictationHisService;

    /**
     * 查询列表
     * @param qry
     * @return
     */
    @RequestMapping(value = "list")
    public MyPage<DictationHisModel> list(@ModelAttribute DictationHisTmpQry qry){
        logger.info("收到查询请求:{}", CommonJsonUtils.toJsonString(qry));
        return dictationHisService.queryPage(qry);
    }

}

