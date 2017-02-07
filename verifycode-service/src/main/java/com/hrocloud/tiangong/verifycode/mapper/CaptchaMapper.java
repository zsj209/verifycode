package com.hrocloud.tiangong.verifycode.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hrocloud.tiangong.verifycode.model.Captcha;
import com.hrocloud.tiangong.verifycode.model.CaptchaImg;

public interface CaptchaMapper {

	public Captcha getCaptchaByCid(@Param("cid") String cid);

	public int insertCaptcha(List<Captcha> captchaList);

	public int insertCaptchaImg(List<CaptchaImg> captchaImgList);

	public int countCaptchaByCidPrefix(@Param("cidPrefix") String cidPrefix);
	
	public String findLastCaptchaValueByCidPrefix(@Param("cidPrefix") String cidPrefix);

}
