package com.shihu.shiro.cache;

import com.shihu.redis.dao.RedisDao;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;

import java.util.Collection;
import java.util.Set;

public class RedioCache implements Cache<String, Object> {
    private String keyPrefix;
    private RedisDao redisDao;

    public RedioCache(RedisDao redisDao,String keyPrefix){
        this.redisDao=redisDao;
        this.keyPrefix=keyPrefix;
    }

    public Object get(String str) throws CacheException {
        return redisDao.get(keyPrefix +""+str);
    }

    public Object put(String str, Object v) throws CacheException {
        return redisDao.put(keyPrefix +""+str,v);
    }

    public Object remove(String str) throws CacheException {
        return redisDao.remove(keyPrefix +""+str);
    }

    public void clear() throws CacheException {
        redisDao.clear();
    }

    public int size() {
        return redisDao.size().intValue();
    }

    public Set<String> keys() {
        return redisDao.keys(keyPrefix+"*");
    }

    public Collection<Object> values() {
        return redisDao.values(keyPrefix+"*");
    }

    public RedioCache setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
        return this;
    }
}
