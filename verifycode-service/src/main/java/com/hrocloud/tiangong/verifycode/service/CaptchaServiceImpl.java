package com.hrocloud.tiangong.verifycode.service;

//import com.hrocloud.redis.RedisClientTemplate;
import com.hrocloud.tiangong.verifycode.client.CaptchaService;
import com.hrocloud.tiangong.verifycode.exception.NoAllowedException;
import com.hrocloud.tiangong.verifycode.exception.TooManyRequestsException;
import com.hrocloud.tiangong.verifycode.mapper.CaptchaMapper;
import com.hrocloud.tiangong.verifycode.mode.CaptchaDO;
import com.hrocloud.tiangong.verifycode.model.Captcha;
import com.hrocloud.tiangong.verifycode.model.CaptchaImg;
import com.hrocloud.tiangong.verifycode.model.Client;
import com.hrocloud.tiangong.verifycode.util.CaptchaHelper;
import com.hrocloud.tiangong.verifycode.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


@Service("captchaService")
public class CaptchaServiceImpl implements CaptchaService {
    private static final Logger logger = LoggerFactory.getLogger(CaptchaServiceImpl.class);

    @Value("${fastdfs.http}")
    private String fstdfsHttp;
    @Resource
    private ClientService clientService;
    @Resource
    private CaptchaMapper captchaMapper;
	@Resource
	private RedisService redisService;

//    @Resource
//    private RedisClientTemplate redisClient;


    private final Random random = new Random();

    private static final int RETRY = 3;

    @Override
    public CaptchaDO requestCaptcha(String clientId, String clientPass, String clientIp)
            throws TooManyRequestsException, NoAllowedException {
        logger.info("start to request captcha>>>>>>clientId:{}, clientPass:{}, clientIp:{}", clientId, clientPass,
                clientIp);
        Client client = clientService.findClientById(clientId);


        try {
            Integer totalCaptchaCount = client.getCaptchaRule().getCaptchaNumber();
            Captcha captcha = getCaptcha(client, totalCaptchaCount);
            if (captcha == null) {
                return null;
            }
            List<CaptchaImg> captchaImgs = captcha.getImgUrls();
            String uuid = CaptchaHelper.getRandomUUID();
            String fdfsKey = captchaImgs.get(random.nextInt(captchaImgs.size())).getFdfsKey();
            if (StringUtils.isEmpty(fdfsKey)) {
                logger.error("has error! tfskey is null");
                return null;
            }
			redisService.put(uuid, captcha.getValue(), Constants.FIVE_MINUTES);
//            redisService.setex(uuid, Constants.FIVE_MINUTES, captcha.getValue());
            CaptchaDO captchaDO = new CaptchaDO(uuid, getImgUrl(fdfsKey));
            logger.info("request captcha>>>>>>success. captchaDO:{}", captchaDO);
            return captchaDO;
        } catch (Exception e) {
            logger.error("request captcha failed>>>>>>exception occured", e);
            return null;
        }
    }

    @Override
    public boolean verifyCaptcha(String clientId, String clientPass, String clientIp, String key, String input)
            throws TooManyRequestsException, NoAllowedException {
        logger.info("start to verify captcha>>>>>>clientId:{}, clientPass:{}, clientIp:{}, key:{}, input:{}", clientId,
                clientPass, clientIp, key, input);
        if (StringUtils.isEmpty(input) || StringUtils.isEmpty(key)) {
            logger.warn("verify captcha failed>>>>>>input or key is null");
            return false;
        }
        Client client = clientService.findClientById(clientId);
        if (client == null || !client.getClientPass().equals(clientPass)) {
            throw new NoAllowedException("clientId or clientPass error");
        }


        try {
			String captchValue =  redisService.get(key);
//            String captchValue = redisClient.get(key);
            if (captchValue != null && captchValue.equalsIgnoreCase(input)) {
                logger.info("verify captcha>>>>>>success. clientId:{}, clientPass:{}, clientIp:{}, key:{}, input:{}",
                        clientId, clientPass, clientIp, key, input);
				redisService.delete(key);
//                redisClient.del(key);
                return true;
            }
            logger.warn(
                    "verify captcha>>>>>>failed. clientId:{}, clientPass:{}, clientIp:{}, key:{}, input:{}",
                    clientId, clientPass, clientIp, key, input);
            return false;
        } catch (Exception e) {
            logger.warn("verify captcha failed>>>>>>exception occured", e);
            return false;
        }
    }

    private Captcha getCaptcha(Client client, int totalCaptchaCount) {
        String cid = CaptchaHelper.genRandomCaptchaCid(client.getClientId(), client.getVersionStr(),
                random.nextInt(totalCaptchaCount) + 1);
        Captcha captcha = getByCid(cid);
        int retryCount = 0;
        while (captcha == null && retryCount < RETRY) {
            cid = CaptchaHelper.genRandomCaptchaCid(client.getClientId(), client.getVersionStr(),
                    random.nextInt(totalCaptchaCount) + 1);
            captcha = getByCid(cid);
            retryCount++;
        }
        return captcha;
    }

    private Captcha getByCid(String cid) {
        return captchaMapper.getCaptchaByCid(cid);
    }

    private String getImgUrl(String tfsKey) {
        return String.format("%s/%s", fstdfsHttp, tfsKey);
    }
}
