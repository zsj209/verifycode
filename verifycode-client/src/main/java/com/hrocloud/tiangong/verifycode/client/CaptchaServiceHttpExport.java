package com.hrocloud.tiangong.verifycode.client;

import com.hrocloud.apigw.client.annoation.ApiGroup;
import com.hrocloud.apigw.client.annoation.ApiParameter;
import com.hrocloud.apigw.client.annoation.HttpApi;
import com.hrocloud.apigw.client.define.SecurityType;
import com.hrocloud.tiangong.verifycode.exception.NoAllowedException;
import com.hrocloud.tiangong.verifycode.exception.TooManyRequestsException;
import com.hrocloud.tiangong.verifycode.exception.VerifycodeHttpCode;
import com.hrocloud.tiangong.verifycode.mode.CaptchaDO;
import com.hrocloud.tiangong.verifycode.mode.CaptchaResp;

/**
 * Created by hanzhihua on 2017/1/11.
 */
@ApiGroup(name = "verifycode", minCode = 8000, maxCode = 9000, codeDefine = VerifycodeHttpCode.class)
public interface CaptchaServiceHttpExport {

    @HttpApi(name = "verifycode.requestCaptcha", desc = "取得验证码", security = SecurityType.None)
    CaptchaResp requestCaptcha(@ApiParameter(required = true, name = "clientId", desc = "接入方id") String clientId,
                               @ApiParameter(required = true, name = "clientPass", desc = "接入方密码") String clientPass,
                               @ApiParameter(required = true, name = "clientIp", desc = "用户端ip") String clientIp);

    @HttpApi(name = "verifycode.verifyCaptcha", desc = "检查验证码", security = SecurityType.None)
    boolean verifyCaptcha(@ApiParameter(required = true, name = "clientId", desc = "接入方id") String clientId,
                          @ApiParameter(required = true, name = "clientPass", desc = "接入方密码") String clientPass,
                          @ApiParameter(required = true, name = "clientIp", desc = "用户端ip") String clientIp,
                          @ApiParameter(required = true, name = "key", desc = "验证码key") String key,
                          @ApiParameter(required = true, name = "input", desc = "用户输入值") String input);
}
