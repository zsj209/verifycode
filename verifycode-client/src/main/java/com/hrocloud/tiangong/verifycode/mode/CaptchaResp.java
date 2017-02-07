package com.hrocloud.tiangong.verifycode.mode;



import com.hrocloud.apigw.client.annoation.Description;

import java.io.Serializable;

/**
 * Created by hanzhihua on 2017/1/11.
 */
@Description("取验证码结果")
public class CaptchaResp implements Serializable {

    private static final long serialVersionUID = 384382844838811377L;

    @Description("验证码的key")
    public String key;

    @Description("验证码的图片地址")
    public String imgUrl;
}
