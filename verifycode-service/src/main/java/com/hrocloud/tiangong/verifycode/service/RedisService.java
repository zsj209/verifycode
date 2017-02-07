package com.hrocloud.tiangong.verifycode.service;

import com.hrocloud.tiangong.verifycode.util.HessianUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by hanzhihua on 2017/1/2.
 */
@Service("redisService")
public class RedisService {

    private Jedis jedis;

    private JedisPool jedisPool;

    @Value("${redis.server}")
    private String redisServer;

    @Value("${redis.port}")
    private String redisPort;

    @PostConstruct
    public void init(){
//        jedis = new Jedis(redisServer,Integer.parseInt(redisPort));
        jedisPool = new JedisPool(redisServer,Integer.parseInt(redisPort));
    }

    public void put(String key,String value,int expire){
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.setex(key, expire, value);
        }finally{
            jedis.close();
        }
    }

    public String get(String key){
        Jedis jedis = jedisPool.getResource();
        try {
        return  jedis.get(key);
        }finally{
            jedis.close();
        }
    }

    public void delete(String key){
        Jedis jedis = jedisPool.getResource();
        try {
        jedis.del(key);
        }finally{
            jedis.close();
        }
    }

    public void putObject(String key,Object value,int expire)throws IOException{
        Jedis jedis = jedisPool.getResource();
        try {
        jedis.setex(key.getBytes(),expire, HessianUtils.encode(value));
        }finally{
            jedis.close();
        }
    }

    public <T> T  getObject(String key)throws IOException{
        Jedis jedis = jedisPool.getResource();
        try {
       return HessianUtils.decode(jedis.get(key.getBytes()));
        }finally{
            jedis.close();
        }
    }


}
