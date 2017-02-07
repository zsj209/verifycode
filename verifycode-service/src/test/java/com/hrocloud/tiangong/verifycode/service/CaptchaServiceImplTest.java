package com.hrocloud.tiangong.verifycode.service;

import com.hrocloud.tiangong.verifycode.common.AbsractTest;
import com.hrocloud.tiangong.verifycode.mode.CaptchaDO;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by hanzhihua on 2017/1/2.
 */
public class CaptchaServiceImplTest extends AbsractTest {
    @Resource
    private CaptchaServiceImpl captchaService;
    @Resource
    private RedisService redisService;

    @Test
    public void testVerifycode(){
        CaptchaDO captchaDO = captchaService.requestCaptcha(1000+"","6cc1c430aed64d3bb8516011195a5b56","127.0.0.1");
        System.out.println(captchaDO);
        String value = redisService.get(captchaDO.getKey());
        System.out.println("vaule:"+value);
        Assert.assertTrue(captchaService.verifyCaptcha(1000+"",
                "6cc1c430aed64d3bb8516011195a5b56","127.0.0.1",captchaDO.getKey(),value));

    }
}
