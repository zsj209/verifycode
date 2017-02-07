package com.hrocloud.tiangong.verifycode.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Entity implements Serializable {
	private static final long serialVersionUID = 1L;
	protected Timestamp gmtCreate;
	protected Timestamp gmtUpdate;

	public Timestamp getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Timestamp gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Timestamp getGmtUpdate() {
		return gmtUpdate;
	}

	public void setGmtUpdate(Timestamp gmtUpdate) {
		this.gmtUpdate = gmtUpdate;
	}

}
