package com.hrocloud.tiangong.verifycode.util;

import java.util.UUID;

public class CaptchaHelper {
	public static String genCaptchaCidPrefix(String clientId, String version) {
		return new StringBuffer().append(clientId).append("-").append(version).toString();
	}

	public static String genRandomCaptchaCid(String clientId, String version, int number) {
		return new StringBuffer().append(clientId).append("-").append(version).append("-").append(number).toString();
	}

	public static String getRandomUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
