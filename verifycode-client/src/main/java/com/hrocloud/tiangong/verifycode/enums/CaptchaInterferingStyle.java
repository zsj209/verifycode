package com.hrocloud.tiangong.verifycode.enums;

/**
 * Created by hanzhihua on 2016/12/31.
 */
public enum CaptchaInterferingStyle {
    INTERFERING_LINE(0), HOT_PIXEL(1);

    private int value = 0;

    private CaptchaInterferingStyle(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}

