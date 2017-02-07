package com.hrocloud.tiangong.verifycode.service;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.hrocloud.tiangong.verifycode.enums.CaptchaValueType;
import com.hrocloud.tiangong.verifycode.enums.RecordStatus;
import com.hrocloud.tiangong.verifycode.exception.CaptchaException;
import com.hrocloud.tiangong.verifycode.mapper.CaptchaMapper;
import com.hrocloud.tiangong.verifycode.mapper.CaptchaRecordMapper;
import com.hrocloud.tiangong.verifycode.model.Captcha;
import com.hrocloud.tiangong.verifycode.model.CaptchaRecord;
import com.hrocloud.tiangong.verifycode.util.CaptchaHelper;
import com.hrocloud.tiangong.verifycode.util.CaptchaUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hrocloud.tiangong.verifycode.exception.ClientException;
import com.hrocloud.tiangong.verifycode.model.CaptchaImg;
import com.hrocloud.tiangong.verifycode.model.Client;

@Service("captchaManagementService")
public class CaptchaManagementService {
	private static final Logger logger = LoggerFactory.getLogger(CaptchaManagementService.class);
	@Resource
	private CaptchaRecordMapper captchaRecordMapper;
	@Resource
	private CaptchaMapper captchaMapper;
	@Resource
	private ClientService clientService;
	@Resource
	private FdfsService fdfsService;

	private final int PER_INSERT = 100;

//	@Transactional
	public void createCaptchaByClientId(String clientId) {
		Client client = clientService.findClientById(clientId);
		if (client == null) {
			throw new ClientException("clientId:" + clientId + " not found");
		}
		long start = System.currentTimeMillis();
		CaptchaRecord captchaRecord = getCaptchaRecordByClientAndVersion(clientId, client.getVersion());
		if (captchaRecord == null) {
			addNewVersionCaptcha(client);
		} else if (RecordStatus.isFailed(captchaRecord.getStatus())) {
			retryAddCaptcha(client, captchaRecord);
		}
		long end = System.currentTimeMillis();
		logger.info("addNewVersionCaptcha function>>>>>>finished. client:{}, time:{}", client, end - start);
	}

	private void addNewVersionCaptcha(Client client) {
		logger.info("start to addNewVersionCaptcha function>>>>>>client:{}", client);
		CaptchaRecord captchaRecord = new CaptchaRecord(client.getClientId(), client.getVersion(),
				RecordStatus.EXECUTION.getCode(), client.getCaptchaRule(), client.getCaptchaTemplate());
		captchaRecordMapper.insert(captchaRecord);
		try {
			Integer totalCount = client.getCaptchaRule().getCaptchaNumber();
			List<String> captchaValues = CaptchaUtil.createCaptchaValues(client.getCaptchaTemplate().getLength(),
					CaptchaValueType.valueOf(client.getCaptchaTemplate().getValueType()), totalCount);
			createCaptcha(captchaValues, captchaRecord, totalCount, client, 1);
		} catch (Exception e) {
			captchaRecordMapper.updateRecordStatus(captchaRecord.getId(), RecordStatus.FAILED.getCode());
			logger.error("addNewVersionCaptcha function>>>>>>failed to add captcha. exception occured!", e);
			return;
		}

		captchaRecordMapper.updateRecordStatus(captchaRecord.getId(), RecordStatus.SUCCESS.getCode());
		clientService.updateVersion(client.getClientId(), client.getNextVersion());
	}

	private void retryAddCaptcha(Client client, CaptchaRecord captchaRecord) {
		logger.info("start to retryAddCaptcha function");
		try {
			String cidPrefix = CaptchaHelper.genCaptchaCidPrefix(client.getClientId(), client.getNextVersionStr());
			String lastCaptchaValue = captchaMapper.findLastCaptchaValueByCidPrefix(cidPrefix);
			int hasCreated = captchaMapper.countCaptchaByCidPrefix(cidPrefix);
			Integer totalCount = client.getCaptchaRule().getCaptchaNumber();
			List<String> captchaValues = CaptchaUtil.createCaptchaValues(client.getCaptchaTemplate().getLength(),
					CaptchaValueType.valueOf(client.getCaptchaTemplate().getValueType()), totalCount - hasCreated,
					totalCount, lastCaptchaValue);
			createCaptcha(captchaValues, captchaRecord, totalCount - hasCreated, client, hasCreated + 1);
		} catch (Exception e) {
			captchaRecordMapper.updateRecordStatus(captchaRecord.getId(), RecordStatus.FAILED.getCode());
			logger.error("addNewVersionCaptcha function>>>>>>failed to add captcha. exception occured!", e);
			return;
		}

		captchaRecordMapper.updateRecordStatus(captchaRecord.getId(), RecordStatus.SUCCESS.getCode());
		clientService.updateVersion(client.getClientId(), client.getNextVersion());
	}

	private boolean createCaptcha(List<String> captchaValues, CaptchaRecord captchaRecord, int totalCreated,
								  Client client, int offset) {
		logger.info(
				"start to createCaptcha function>>>>>>size of captchaValues:{}, totalCreated:{}, offset:{}, client:{}, captchaRecord:{}",
				captchaValues.size(), totalCreated, offset, client, captchaRecord);
		try {
			int hasCreated = 0;
			int actualCreated = totalCreated - hasCreated > PER_INSERT ? PER_INSERT : totalCreated - hasCreated;
			while (hasCreated < totalCreated) {
				List<Captcha> captchaList = new ArrayList<Captcha>();
				List<CaptchaImg> captchaImgList = new ArrayList<CaptchaImg>();
				for (int captchaNumber = 0; captchaNumber < actualCreated; captchaNumber++) {
					String captchaValue = captchaValues.get(hasCreated + captchaNumber);
					String cid = CaptchaHelper.genRandomCaptchaCid(client.getClientId(), client.getNextVersionStr(),
							offset);
					Captcha captcha = new Captcha(cid, captchaValue);
					captchaList.add(captcha);
					for (int imgNumber = 0; imgNumber < client.getCaptchaRule().getImgNumber(); imgNumber++) {
						BufferedImage bufferedImage = CaptchaUtil.createCaptchaImage(client.getCaptchaTemplate(),
								captchaValue);
						String fdfsFileName = fdfsService.saveFile(bufferedImage);
						captchaImgList.add(new CaptchaImg(cid, fdfsFileName));
					}
					++offset;
				}
				if (insert(captchaList, captchaImgList)) {
					hasCreated += actualCreated;
				}
			}
		} catch (Exception e) {
			captchaRecordMapper.updateRecordStatus(captchaRecord.getId(), RecordStatus.FAILED.getCode());
			logger.error(
					"createCaptcha function>>>>>>failed to create. exception occured." +
							" size of captchaValues:{}, totalCreated:{}, offset:{}, client:{}, captchaRecord:{}",
					captchaValues.size(), totalCreated, offset, client, captchaRecord);
			logger.error(e.getMessage(),e);
			throw new CaptchaException(e.getMessage());
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = CaptchaException.class)
	public boolean insert(List<Captcha> captchaList, List<CaptchaImg> captchaImgList) throws CaptchaException {
		logger.info("start to insert captcha function>>>>>>size of captchaValues:{}, captchaImgList:{}",
				captchaList.size(), captchaImgList.size());
		long currentTime = System.currentTimeMillis();
		logger.info("start inserting.......................................");
		int ret1 = captchaMapper.insertCaptcha(captchaList);
		int ret2 = captchaMapper.insertCaptchaImg(captchaImgList);
		if (ret1 <= 0 || ret2 <= 0) {
			logger.error("insert failed>>>>>>ret1:{}, ret2:{}, size of captchaList:{}, size of captchaImgList:{}", ret1,
					ret2, captchaList.size(), captchaImgList.size());
			throw new CaptchaException("failed to insert");
		}
		logger.warn("it cost:"+(System.currentTimeMillis()-currentTime)+".ms .........................................");
		return true;
	}

	public CaptchaRecord getCaptchaRecordByClientAndVersion(String clientId, Integer version) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("clientId", clientId);
		map.put("currentVersion", version);
		return captchaRecordMapper.getCaptchaRecord(map);
	}
}
