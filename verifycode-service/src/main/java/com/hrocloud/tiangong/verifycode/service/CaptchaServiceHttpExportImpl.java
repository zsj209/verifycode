package com.hrocloud.tiangong.verifycode.service;

import com.hrocloud.apigw.client.annoation.ApiParameter;
import com.hrocloud.apigw.client.dubboext.DubboExtProperty;
import com.hrocloud.tiangong.verifycode.client.CaptchaService;
import com.hrocloud.tiangong.verifycode.client.CaptchaServiceHttpExport;
import com.hrocloud.tiangong.verifycode.exception.VerifycodeHttpCode;
import com.hrocloud.tiangong.verifycode.mode.CaptchaDO;
import com.hrocloud.tiangong.verifycode.mode.CaptchaResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by hanzhihua on 2017/1/11.
 */
@Service("captchaServiceHttpExportImpl")
public class CaptchaServiceHttpExportImpl implements CaptchaServiceHttpExport {

    private final static Logger logger = LoggerFactory.getLogger(CaptchaServiceHttpExportImpl.class);

    @Resource
    private CaptchaService captchaService;

    @Override
    public CaptchaResp requestCaptcha(@ApiParameter(required = true, name = "clientId", desc = "接入方id") String clientId, @ApiParameter(required = true, name = "clientPass", desc = "接入方密码") String clientPass, @ApiParameter(required = true, name = "clientIp", desc = "用户端ip") String clientIp) {

        try {
            CaptchaDO captchaDO = captchaService.requestCaptcha(clientId, clientPass, clientIp);
            if(captchaDO == null)
                return null;

            CaptchaResp resp = new CaptchaResp();
            resp.imgUrl = captchaDO.getImgUrl();
            resp.key = captchaDO.getKey();

            return resp;
        } catch (RuntimeException e) {
            logger.error("internal exception.", e);
            DubboExtProperty
                    .setErrorCode(VerifycodeHttpCode.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @Override
    public boolean verifyCaptcha(@ApiParameter(required = true, name = "clientId", desc = "接入方id") String clientId, @ApiParameter(required = true, name = "clientPass", desc = "接入方密码") String clientPass, @ApiParameter(required = true, name = "clientIp", desc = "用户端ip") String clientIp, @ApiParameter(required = true, name = "key", desc = "验证码key") String key, @ApiParameter(required = true, name = "input", desc = "用户输入值") String input) {
        try{
        boolean result = captchaService.verifyCaptcha(clientId,clientPass,clientIp,key,input);
        return result;
        } catch (RuntimeException e) {
            logger.error("internal exception.", e);
            DubboExtProperty
                    .setErrorCode(VerifycodeHttpCode.INTERNAL_SERVER_ERROR);
            return false;
        }

    }
}
