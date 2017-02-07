package com.hrocloud.tiangong.verifycode.mode;

import java.io.Serializable;

/**
 * Created by hanzhihua on 2016/12/31.
 */
public class CaptchaDO implements Serializable {

    private static final long serialVersionUID = 384382844838811377L;

    private String key;

    private String imgUrl;

    public CaptchaDO(String key, String imgUrl) {
        this.key = key;
        this.imgUrl = imgUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "CaptchaDo [key=" + key + ", imgUrl=" + imgUrl + "]";
    }

}


