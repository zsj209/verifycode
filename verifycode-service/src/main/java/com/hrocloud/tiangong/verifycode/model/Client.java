package com.hrocloud.tiangong.verifycode.model;

public class Client extends Entity {
	private static final long serialVersionUID = 8625958840985014408L;
	private Long id;
	private String clientId;
	private String clientPass;
	private String description;
	private Integer version;
	private boolean active;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Client client = (Client) o;

		if (active != client.active) return false;
		if (id != null ? !id.equals(client.id) : client.id != null) return false;
		if (clientId != null ? !clientId.equals(client.clientId) : client.clientId != null) return false;
		if (clientPass != null ? !clientPass.equals(client.clientPass) : client.clientPass != null) return false;
		if (description != null ? !description.equals(client.description) : client.description != null) return false;
		if (version != null ? !version.equals(client.version) : client.version != null) return false;
		if (captchaTemplate != null ? !captchaTemplate.equals(client.captchaTemplate) : client.captchaTemplate != null)
			return false;
		return captchaRule != null ? captchaRule.equals(client.captchaRule) : client.captchaRule == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
		result = 31 * result + (clientPass != null ? clientPass.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (version != null ? version.hashCode() : 0);
		result = 31 * result + (active ? 1 : 0);
		result = 31 * result + (captchaTemplate != null ? captchaTemplate.hashCode() : 0);
		result = 31 * result + (captchaRule != null ? captchaRule.hashCode() : 0);
		return result;
	}

	private CaptchaTemplate captchaTemplate;
	private CaptchaRule captchaRule;

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

	public String getClientPass() {
		return clientPass;
	}

	public void setClientPass(String clientPass) {
		this.clientPass = clientPass;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CaptchaTemplate getCaptchaTemplate() {
		return captchaTemplate;
	}

	public void setCaptchaTemplate(CaptchaTemplate captchaTemplate) {
		this.captchaTemplate = captchaTemplate;
	}

	public CaptchaRule getCaptchaRule() {
		return captchaRule;
	}

	public void setCaptchaRule(CaptchaRule captchaRule) {
		this.captchaRule = captchaRule;
	}

	public String getVersionStr() {
		return version != null ? String.format("%05d", this.version) : "";
	}

	public String getNextVersionStr() {
		return version != null ? String.format("%05d", getNextVersion()) : "";
	}
	
	public Integer getNextVersion() {
		return version != null ? this.version + 1 : 1;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", clientId=" + clientId + ", clientPass=" + clientPass + ", description="
				+ description + ", version=" + version + ", active=" + active + ", captchaTemplate=" + captchaTemplate
				+ ", captchaRule=" + captchaRule + "]";
	}

}
