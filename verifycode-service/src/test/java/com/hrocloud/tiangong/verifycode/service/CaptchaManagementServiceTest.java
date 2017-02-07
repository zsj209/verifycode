package com.hrocloud.tiangong.verifycode.service;

import com.hrocloud.tiangong.verifycode.common.AbsractTest;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by hanzhihua on 2017/1/2.
 */
public class CaptchaManagementServiceTest extends AbsractTest {

    @Resource
    private CaptchaManagementService captchaManagementService;

    @Test
    public void initData(){
        captchaManagementService.createCaptchaByClientId(1000+"");
    }
}
