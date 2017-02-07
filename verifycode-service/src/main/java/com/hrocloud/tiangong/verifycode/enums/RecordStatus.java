package com.hrocloud.tiangong.verifycode.enums;

public enum RecordStatus {
	INIT(0), EXECUTION(10), SUCCESS(20), FAILED(-10);

	private int code = 0;

	private RecordStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static boolean isExecution(int code) {
		return code == RecordStatus.EXECUTION.getCode();
	}
	
	public static boolean isSuccess(int code) {
		return code == RecordStatus.SUCCESS.getCode();
	}
	
	public static boolean isFailed(int code) {
		return code == RecordStatus.FAILED.getCode();
	}
	
	public static boolean isInit(int code) {
		return code == RecordStatus.INIT.getCode();
	}
}
