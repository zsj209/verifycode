package com.hrocloud.tiangong.verifycode.exception;

/**
 * Created by hanzhihua on 2016/12/31.
 */
public class NoAllowedException extends RuntimeException{
    public NoAllowedException() {
    }

    public NoAllowedException(String message) {
        super(message);
    }

    public NoAllowedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoAllowedException(Throwable cause) {
        super(cause);
    }

    public NoAllowedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
