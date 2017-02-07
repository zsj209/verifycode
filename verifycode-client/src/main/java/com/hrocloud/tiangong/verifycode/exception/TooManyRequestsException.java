package com.hrocloud.tiangong.verifycode.exception;

/**
 * Created by hanzhihua on 2016/12/31.
 */
public class TooManyRequestsException extends RuntimeException{

    public TooManyRequestsException() {
    }

    public TooManyRequestsException(String message) {
        super(message);
    }

    public TooManyRequestsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyRequestsException(Throwable cause) {
        super(cause);
    }

    public TooManyRequestsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
