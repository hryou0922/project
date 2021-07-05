package com.hry.project.dictation.service.impl;

import com.hry.project.dictation.BaseTest;
import com.hry.project.dictation.constant.Constants;
import com.hry.project.dictation.dto.req.word.DictationHisTmpBatchUpdateReq;
import com.hry.project.dictation.model.DictationHisTmpModel;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.AopTestUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.spy;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2021/6/15 11:41
 */
public class DictationHisTmpServiceImplTest extends BaseTest {
    @Autowired
    protected ApplicationContext applicationContext;

    private DictationHisTmpServiceImpl mock;

    @PostConstruct
    public void init(){
        //  * 获取原对象（非spring的代理bean），然后再进行spy，以替代直接使用spybean来spy含有transactional注解的类，防止抛出NotAMockException
        DictationHisTmpServiceImpl dictationHisTmpService = AopTestUtils.getUltimateTargetObject(applicationContext.getBean(DictationHisTmpServiceImpl.class));
        mock = spy(dictationHisTmpService);

        // spy
        doAnswer(new Answer<List<DictationHisTmpModel>>() {
            @Override
            public List<DictationHisTmpModel> answer(InvocationOnMock invocationOnMock) throws Throwable {
                List<DictationHisTmpModel> list = new ArrayList<>();
                DictationHisTmpModel tmp = new DictationHisTmpModel();
                tmp.setId(1L);
                tmp.setCreateTime(new Date());
                // tmp.setGroupId();
                tmp.setWord("黄莺");
                tmp.setResult(Constants.RESULT_FAIL);
                tmp.setDes("");

                list.add(tmp);

                return list;
            }
        }).when(mock).listByIds(any());
    }

    @Test
    public void archive(){
        DictationHisTmpBatchUpdateReq req = new DictationHisTmpBatchUpdateReq();
        req.setIds(Arrays.asList(1L,2L));
        mock.archive(req);
    }
}
