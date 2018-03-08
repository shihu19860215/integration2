package com.shihu.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;

/**
 * 不配置默认使用内存缓存
 * 可以在spring 配置文件里配置其他CacheManager
 */
public class CacheFactory{
    private static CacheManager memoryCacheManager=new MemoryConstrainedCacheManager();
    private static CacheManager cacheManager=memoryCacheManager;
    public static Cache getCache(String key){
        return cacheManager.getCache(key);
    }

    public static Cache getMemoryCache(String key){
        return memoryCacheManager.getCache(key);
    }
    public void setCacheManager(CacheManager cm) {
        cacheManager=cm;
    }
}
