package com.hrocloud.tiangong.verifycode.model;

public class CaptchaRecord extends Entity {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String clientId;
	private Integer currentVersion;
	private Integer status;
	private CaptchaRule captchaRule;
	private CaptchaTemplate captchaTemplate;

	public CaptchaRecord() {

	}

	public CaptchaRecord(String clientId, Integer currentVersion, Integer status, CaptchaRule captchaRule,
			CaptchaTemplate captchaTemplate) {
		this.clientId = clientId;
		this.currentVersion = currentVersion;
		this.status = status;
		this.captchaRule = captchaRule;
		this.captchaTemplate = captchaTemplate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Integer getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public CaptchaRule getCaptchaRule() {
		return captchaRule;
	}

	public void setCaptchaRule(CaptchaRule captchaRule) {
		this.captchaRule = captchaRule;
	}

	public CaptchaTemplate getCaptchaTemplate() {
		return captchaTemplate;
	}

	public void setCaptchaTemplate(CaptchaTemplate captchaTemplate) {
		this.captchaTemplate = captchaTemplate;
	}

	@Override
	public String toString() {
		return "CaptchaRecord [id=" + id + ", clientId=" + clientId + ", currentVersion=" + currentVersion + ", status="
				+ status + ", captchaRule=" + captchaRule + ", captchaTemplate=" + captchaTemplate + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CaptchaRecord that = (CaptchaRecord) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (clientId != null ? !clientId.equals(that.clientId) : that.clientId != null) return false;
		if (currentVersion != null ? !currentVersion.equals(that.currentVersion) : that.currentVersion != null)
			return false;
		if (status != null ? !status.equals(that.status) : that.status != null) return false;
		if (captchaRule != null ? !captchaRule.equals(that.captchaRule) : that.captchaRule != null) return false;
		return captchaTemplate != null ? captchaTemplate.equals(that.captchaTemplate) : that.captchaTemplate == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
		result = 31 * result + (currentVersion != null ? currentVersion.hashCode() : 0);
		result = 31 * result + (status != null ? status.hashCode() : 0);
		result = 31 * result + (captchaRule != null ? captchaRule.hashCode() : 0);
		result = 31 * result + (captchaTemplate != null ? captchaTemplate.hashCode() : 0);
		return result;
	}
}
