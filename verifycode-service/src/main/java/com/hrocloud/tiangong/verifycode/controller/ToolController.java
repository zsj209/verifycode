package com.hrocloud.tiangong.verifycode.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tool")
public class ToolController {
	private static final Logger logger = LoggerFactory.getLogger(ToolController.class);

	
	@RequestMapping(value = "/remove_cache", method = RequestMethod.GET)
	@ResponseBody
	public void generateCaptchaPost(@RequestParam(value = "key", required = true) String key,
			HttpServletRequest httpServletRequest) {
		logger.info("{}>>>>>>key:{}", httpServletRequest.getRequestURI(), key);
//		tairIndex.remove(key);
//		logger.info("{}>>>>>>key:{}, cache:{}", httpServletRequest.getRequestURI(), key, tairIndex.get(key));
	}
}
