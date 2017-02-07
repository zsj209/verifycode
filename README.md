**verifycode:通用验证服务**
使用方法：

对外提供dubbo接口 **com.hrocloud.tiangong.verifycode.client.CaptchaService**，
包括两个方法，一个是获取验证码，一个是校验验证码
对于天弓 clientId就是1000，clientPass是6cc1c430aed64d3bb8516011195a5b56
clientIp就是当前客户端的ip

 
 
 
    
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
