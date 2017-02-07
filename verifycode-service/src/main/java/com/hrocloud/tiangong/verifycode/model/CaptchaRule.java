package com.hrocloud.tiangong.verifycode.model;

public class CaptchaRule extends Entity {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer captchaNumber;
	private Integer imgNumber;
	private String scheduler;
	private boolean autoUpdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCaptchaNumber() {
		return captchaNumber;
	}

	public void setCaptchaNumber(Integer captchaNumber) {
		this.captchaNumber = captchaNumber;
	}

	public Integer getImgNumber() {
		return imgNumber;
	}

	public void setImgNumber(Integer imgNumber) {
		this.imgNumber = imgNumber;
	}

	public String getScheduler() {
		return scheduler;
	}

	public void setScheduler(String scheduler) {
		this.scheduler = scheduler;
	}

	public boolean isAutoUpdate() {
		return autoUpdate;
	}

	public void setAutoUpdate(boolean autoUpdate) {
		this.autoUpdate = autoUpdate;
	}

	@Override
	public String toString() {
		return "ClientCaptchaRule [id=" + id + ", captchaNumber=" + captchaNumber + ", imgNumber=" + imgNumber
				+ ", scheduler=" + scheduler + ", autoUpdate=" + autoUpdate + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CaptchaRule that = (CaptchaRule) o;

		if (autoUpdate != that.autoUpdate) return false;
		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (captchaNumber != null ? !captchaNumber.equals(that.captchaNumber) : that.captchaNumber != null)
			return false;
		if (imgNumber != null ? !imgNumber.equals(that.imgNumber) : that.imgNumber != null) return false;
		return scheduler != null ? scheduler.equals(that.scheduler) : that.scheduler == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (captchaNumber != null ? captchaNumber.hashCode() : 0);
		result = 31 * result + (imgNumber != null ? imgNumber.hashCode() : 0);
		result = 31 * result + (scheduler != null ? scheduler.hashCode() : 0);
		result = 31 * result + (autoUpdate ? 1 : 0);
		return result;
	}
}
