package com.ruoyi.common.utils.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class RedisUtils {
    /**
     * 获取hash表中所有key
     * @param name
     * @return
     */
    public static Set<String> getHashAllKey(String name){
        Jedis jedis = null;
        try {
            jedis = MyJedisPool.getJedis();
            return jedis.hkeys(name);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyJedisPool.returnJedisOjbect(jedis);
        }
        return null;
    }

    /**
     * 从redis hash表中获取
     * @param hashName
     * @param key
     * @return
     */
    public static String getHashKV(String hashName,String key){
        Jedis jedis = null;
        try {
            jedis = MyJedisPool.getJedis();
            return jedis.hget(hashName, key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyJedisPool.returnJedisOjbect(jedis);
        }
        return null;
    }

    /**
     * 删除hash表的键值对
     * @param hashName
     * @param key
     */
    public static Long delHashKV(String hashName,String key){
        Jedis jedis = null;
        try {
            jedis = MyJedisPool.getJedis();
            return jedis.hdel(hashName,key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyJedisPool.returnJedisOjbect(jedis);
        }
        return null;
    }

    /**
     * 存放hash表键值对
     * @param hashName
     * @param key
     * @param value
     */
    public static Long setHashKV(String hashName,String key,String value){
        Jedis jedis = null;
        try {
            jedis = MyJedisPool.getJedis();
            return jedis.hset(hashName,key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyJedisPool.returnJedisOjbect(jedis);
        }
        return null;
    }

    /**
     * 删除键值对
     * @param k
     * @return
     */
    public static Long delKV(String k){
        Jedis jedis = null;
        try {
            jedis = MyJedisPool.getJedis();
            return jedis.del(k);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyJedisPool.returnJedisOjbect(jedis);
        }
        return null;
    }

    /**
     * 放键值对
     * 永久
     * @param k
     * @param v
     */
    public static String setKV(String k, String v)
    {
        Jedis jedis = null;
        try {
            jedis = MyJedisPool.getJedis();
            return jedis.set(k, v);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyJedisPool.returnJedisOjbect(jedis);
        }
        return null;
    }


    /**
     * 放键值对
     *
     * @param k
     * @param v
     */
    public static String setKV(String k,int second, String v)
    {
        Jedis jedis = null;
        try {
            jedis = MyJedisPool.getJedis();
            return jedis.setex(k,second, v);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyJedisPool.returnJedisOjbect(jedis);
        }
        return null;
    }

    /**
     * 根据key取value
     *
     * @param k
     * @return
     */
    public static String getKV(String k)
    {
        Jedis jedis = null;
        try {
            jedis = MyJedisPool.getJedis();
            return jedis.get(k);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            MyJedisPool.returnJedisOjbect(jedis);
        }
        return null;
    }
}
