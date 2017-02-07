package com.hrocloud.tiangong.verifycode.exception;

import com.hrocloud.apigw.client.define.AbstractReturnCode;

/**
 * Created by hanzhihua on 2017/1/11.
 */
public class VerifycodeHttpCode extends AbstractReturnCode {

    protected VerifycodeHttpCode(int code, AbstractReturnCode display) {
        super(code, display);
    }

    protected VerifycodeHttpCode(String desc, int code) {
        super(desc, code);
    }

    public final static int        C_INTERNAL_SERVER_ERROR = 8001;
    public final static AbstractReturnCode INTERNAL_SERVER_ERROR   = new VerifycodeHttpCode( "服务器内部错误", C_INTERNAL_SERVER_ERROR);



}

