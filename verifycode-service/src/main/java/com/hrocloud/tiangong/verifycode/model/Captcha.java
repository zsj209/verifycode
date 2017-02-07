package com.hrocloud.tiangong.verifycode.model;

import java.util.List;

public class Captcha {
	private Long id;
	private String cid;
	private String value;
	private List<CaptchaImg> imgUrls;

	public Captcha() {

	}

	public Captcha(String cid, String value) {
		this.cid = cid;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<CaptchaImg> getImgUrls() {
		return imgUrls;
	}

	public void setImgUrls(List<CaptchaImg> imgUrls) {
		this.imgUrls = imgUrls;
	}

	@Override
	public String toString() {
		return "Captcha [id=" + id + ", cid=" + cid + ", value=" + value + ", imgUrls=" + imgUrls + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Captcha captcha = (Captcha) o;

		if (id != null ? !id.equals(captcha.id) : captcha.id != null) return false;
		if (cid != null ? !cid.equals(captcha.cid) : captcha.cid != null) return false;
		if (value != null ? !value.equals(captcha.value) : captcha.value != null) return false;
		return imgUrls != null ? imgUrls.equals(captcha.imgUrls) : captcha.imgUrls == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (cid != null ? cid.hashCode() : 0);
		result = 31 * result + (value != null ? value.hashCode() : 0);
		result = 31 * result + (imgUrls != null ? imgUrls.hashCode() : 0);
		return result;
	}
}
