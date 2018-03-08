package com.shihu.redis.dao.impl;

import com.shihu.redis.dao.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Repository
public class RedisDaoImpl<K,V> implements RedisDao<K,V> {
    @Autowired
    public RedisTemplate<K,V> redisTemplate;

    public V get(K k){
        return redisTemplate.opsForValue().get(k);
    }
    public V put(K k, V v) {
        redisTemplate.opsForValue().set(k,v);
        return v;
    }

    public V remove(K k) {
        V v=redisTemplate.opsForValue().get(k);
        if(null!=v){
            redisTemplate.delete(k);
        }
        return v;
    }

    public void clear() {
        redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return  "ok";
            }
        });
    }

    public Long size() {
        return redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection){
                return connection.dbSize();
            }
        });
    }

    public Set<K> keys(K k) {
        return redisTemplate.keys(k);
    }

    public List<V> values(K pattern) {
        Set<K> keys=redisTemplate.keys(pattern);
        if(!CollectionUtils.isEmpty(keys)){
            List<V> vList=new ArrayList<V>();
            for(K k:keys){
                vList.add(redisTemplate.opsForValue().get(k));
            }
            return vList;
        }else {
            return Collections.emptyList();
        }
    }
}