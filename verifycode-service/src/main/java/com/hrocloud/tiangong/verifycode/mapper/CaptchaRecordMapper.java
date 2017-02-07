package com.hrocloud.tiangong.verifycode.mapper;

import java.util.Map;

import com.hrocloud.tiangong.verifycode.model.CaptchaRecord;
import org.apache.ibatis.annotations.Param;

public interface CaptchaRecordMapper {
	public CaptchaRecord getCaptchaRecord(Map<String, Object> map);

	public int insert(CaptchaRecord captchaRecord);

	public int updateRecordStatus(@Param("id") Long id, @Param("status") Integer status);
}
