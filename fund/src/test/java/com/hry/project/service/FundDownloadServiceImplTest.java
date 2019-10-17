package com.hry.project.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class FundDownloadServiceImplTest extends TestBase{
    @Autowired
    private IFundDownloadService fundDownloadService;

    @Test
    public void download(){
        fundDownloadService.download();
    }
}
