package com.hrocloud.tiangong.verifycode.enums;

/**
 * Created by hanzhihua on 2016/12/31.
 */
public enum CaptchaValueType {

    CHARACTER_AND_DIGIT(0), CHARACTER(1), DIGIT(2);

    private int type = 0;

    private CaptchaValueType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public boolean isCharacterAndDigit(int type) {
        return type == CHARACTER_AND_DIGIT.getType();
    }

    public boolean isCharacter(int type) {
        return type == CHARACTER.getType();
    }

    public boolean isDigit(int type) {
        return type == DIGIT.getType();
    }

    public static CaptchaValueType valueOf(int type) {
        switch (type) {
            case 0:
                return CHARACTER_AND_DIGIT;
            case 1:
                return CHARACTER;
            case 2:
                return DIGIT;
            default:
                return CHARACTER_AND_DIGIT;
        }
    }
}
