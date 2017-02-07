package com.hrocloud.tiangong.verifycode.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrocloud.tiangong.verifycode.model.CaptchaRecord;
import com.hrocloud.tiangong.verifycode.model.Client;
import com.hrocloud.tiangong.verifycode.service.CaptchaManagementService;
import com.hrocloud.tiangong.verifycode.service.ClientService;

@Controller
@RequestMapping("/captcha_management")
public class CaptchaManagementController {
	private static final Logger logger = LoggerFactory.getLogger(CaptchaManagementController.class);

	@Resource
	private CaptchaManagementService captchaManagementService;
	@Resource
	private ClientService clientService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@ResponseBody
	public String generateCaptchaGet(@RequestParam(value = "clientId", required = true) String clientId,
			HttpServletRequest httpServletRequest) {
		Client client = clientService.findClientById(clientId);
		CaptchaRecord captchaRecord = captchaManagementService.getCaptchaRecordByClientAndVersion(clientId, client.getVersion());
		logger.info("{}>>>>>>method:{}, clientId:{}", httpServletRequest.getRequestURI(),
				httpServletRequest.getMethod(), clientId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("client", client);
		map.put("rule", client.getCaptchaRule());
		map.put("record", captchaRecord);
//		return CommonUtil.render("templates/createCaptcha.vm", map);
		return "";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public void generateCaptchaPost(@RequestParam(value = "clientId", required = true) String clientId,
			HttpServletRequest httpServletRequest) {
		logger.info("{}>>>>>>clientId:{}", httpServletRequest.getRequestURI(), clientId);
		captchaManagementService.createCaptchaByClientId(clientId);
	}

}
