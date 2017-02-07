package com.hrocloud.tiangong.verifycode.service;

import com.hrocloud.tiangong.verifycode.common.AbsractTest;
import com.hrocloud.tiangong.verifycode.model.Client;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by hanzhihua on 2017/1/2.
 */
public class RedisServiceTest extends AbsractTest {

    @Resource
    private RedisService redisService;

    @Test
    public void testRedisService()throws Exception{
        int expire = 60*5;
        String key = "key1";
        Client client = new Client();
        client.setActive(true);
        client.setClientId("1000");
        client.setClientPass("fafds");
        redisService.putObject(key,client,expire);

        Client client1 = redisService.getObject(key);

        Assert.assertTrue(client.equals(client1));
    }
}
