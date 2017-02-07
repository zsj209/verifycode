package com.hrocloud.tiangong.verifycode.client;

import com.hrocloud.tiangong.verifycode.exception.NoAllowedException;
import com.hrocloud.tiangong.verifycode.exception.TooManyRequestsException;
import com.hrocloud.tiangong.verifycode.mode.CaptchaDO;

/**
 * Created by hanzhihua on 2016/12/31.
 */
public interface CaptchaService {

    /**
     * 获取验证码
     *
     * @param clientId
     * @param clientPass
     * @param clientIp
     * @return
     * @throws TooManyRequestsException
     * @throws NoAllowedException
     */
    CaptchaDO requestCaptcha(String clientId, String clientPass, String clientIp)
            throws TooManyRequestsException, NoAllowedException;

    /**
     * 校验验证码
     *
     * @param clientId
     * @param clientPass
     * @param clientIp
     * @param key
     * @param input
     * @return
     * @throws TooManyRequestsException
     * @throws NoAllowedException
     */
    boolean verifyCaptcha(String clientId, String clientPass, String clientIp, String key, String input)
            throws TooManyRequestsException, NoAllowedException;
}
