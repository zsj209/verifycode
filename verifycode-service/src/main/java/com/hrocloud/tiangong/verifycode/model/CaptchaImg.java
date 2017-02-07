package com.hrocloud.tiangong.verifycode.model;

public class CaptchaImg {
	private Long id;
	private String captchaCid;
	private String fdfsKey;

	public CaptchaImg() {
		
	}

	public CaptchaImg(String captchaCid, String fdfsKey) {
		this.captchaCid = captchaCid;
		this.fdfsKey = fdfsKey;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCaptchaCid() {
		return captchaCid;
	}

	public void setCaptchaCid(String captchaCid) {
		this.captchaCid = captchaCid;
	}

	public String getFdfsKey() {
		return fdfsKey;
	}

	public void setFdfsKey(String fdfsKey) {
		this.fdfsKey = fdfsKey;
	}
	
	@Override
	public String toString() {
		return "CaptchaImg [id=" + id + ", captchaCid=" + captchaCid + ", fdfsKey=" + fdfsKey + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CaptchaImg that = (CaptchaImg) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (captchaCid != null ? !captchaCid.equals(that.captchaCid) : that.captchaCid != null) return false;
		return fdfsKey != null ? fdfsKey.equals(that.fdfsKey) : that.fdfsKey == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (captchaCid != null ? captchaCid.hashCode() : 0);
		result = 31 * result + (fdfsKey != null ? fdfsKey.hashCode() : 0);
		return result;
	}
}
