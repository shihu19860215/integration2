package com.shihu.shiro.cache;

import com.shihu.redis.dao.RedisDao;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RedisCacheManager<K,V> implements CacheManager {
    private Map<String,Cache> redisCacheMap=new HashMap<String,Cache>();
    @Autowired
    private RedisDao redisDao;
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache cache=redisCacheMap.get(name);
        if(null==cache){
            cache=new RedioCache(redisDao,name);
            redisCacheMap.put(name,cache);
        }
        return cache;
    }
}
