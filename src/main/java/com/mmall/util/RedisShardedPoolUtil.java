package com.mmall.util;

import com.mmall.common.RedisShardedPool;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;

@Slf4j
public class RedisShardedPoolUtil {

    /**
     * 设置key的有效期，单位是秒
     * @param key
     * @param exTime
     * @return
     */
    public static Long expire(String key, int exTime) {
        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.expire(key, exTime);
        } catch (Exception e) {
            log.error("expire key:{}", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }

        RedisShardedPool.returnResource(jedis);
        return result;
    }


    /**
     * Set的时候设置有效期，单位为秒
     * @param key
     * @param value
     * @param exTime
     * @return
     */
    public static String setEx(String key, String value, int exTime) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setex(key, exTime, value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }

        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static String set(String key, String value) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }

        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static String get(String key) {
        ShardedJedis jedis = null;
        String result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("set key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }

        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static Long del(String key) {
        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{} error", key, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }

        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static Long setNx(String key, String value) {
        ShardedJedis jedis = null;
        Long result = null;

        try {
            jedis = RedisShardedPool.getJedis();
            result = jedis.setnx(key, value);
        } catch (Exception e) {
            log.error("setex key:{} value:{} error", key, value, e);
            RedisShardedPool.returnBrokenResource(jedis);
            return result;
        }

        RedisShardedPool.returnResource(jedis);
        return result;
    }

    public static void main(String[] args) {
        ShardedJedis jedis = RedisShardedPool.getJedis();
        RedisShardedPoolUtil.set("zphh", "zphhhh");
        String value = RedisShardedPoolUtil.get("zphh");

        RedisShardedPoolUtil.setEx("keyex", "value", 60*10);

        RedisShardedPoolUtil.expire("zphh", 60*20);

        RedisShardedPoolUtil.del("zphh");
        System.out.println("end");
    }


}