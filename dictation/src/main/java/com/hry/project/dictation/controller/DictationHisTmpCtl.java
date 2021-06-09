package com.hry.project.dictation.controller;


import com.hry.project.dictation.dto.page.CommonRsp;
import com.hry.project.dictation.dto.page.MyPage;
import com.hry.project.dictation.dto.req.word.DictationHisTmpQry;
import com.hry.project.dictation.model.DictationHisTmpModel;
import com.hry.project.dictation.service.DictationHisTmpService;
import com.hry.project.dictation.utils.CommonJsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/dictation-his-tmp")
public class DictationHisTmpCtl {
    private static final Logger logger = LoggerFactory.getLogger(DictationHisTmpCtl.class);

    @Autowired
    private DictationHisTmpService dictationHisTmpService;

    /**
     * 查询列表
     * @param qry
     * @return
     */
    @RequestMapping(value = "list")
    public MyPage<DictationHisTmpModel> list(@ModelAttribute DictationHisTmpQry qry){
        logger.info("收到登录请求:{}", CommonJsonUtils.toJsonString(qry));
        return dictationHisTmpService.queryPage(qry);
    }

    /**
     * 更新记录为失败
     * @param id
     * @return
     */
    @RequestMapping(value = "update_fail")
    public CommonRsp update2Fail(@RequestParam("id") String id){
        return  CommonRsp.getOkCommonRsp();
    }

    /**
     * 更新记录为成功
     * @param id
     * @return
     */
    @RequestMapping(value = "update_success")
    public CommonRsp update2Success(@RequestParam("id") String id){
        return  CommonRsp.getOkCommonRsp();
    }

    /**
     * 归档：
     *  将所有记录归档
     * @return
     */
    @RequestMapping(value = "f")
    public CommonRsp f(){
        return  CommonRsp.getOkCommonRsp();
    }

}

