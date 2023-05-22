package com.hry.project.capcut.service;

import com.hry.project.capcut.TestBase;
import com.hry.project.capcut.service.impl.CapCutConfigFileServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: huangrongyou@yixin.im
 * @date: 2023/5/19 22:20
 */
public class CapCutConfigFileServiceImplTest extends TestBase {
    @Autowired
    private CapCutConfigFileServiceImpl capCutConfigFileService;

    @Test
    public void execute(){
        capCutConfigFileService.execute();
    }
}
