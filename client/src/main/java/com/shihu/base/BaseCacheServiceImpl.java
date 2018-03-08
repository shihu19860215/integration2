package com.shihu.base;


import com.shihu.cache.CacheFactory;
import org.apache.shiro.cache.Cache;

import java.util.List;

public abstract class BaseCacheServiceImpl<T> extends BaseServiceImpl<T> {
    private Cache<String,Object> cache;
    private String listAllKey="All";



    public T get(Long id) {
        T t=null;
        Object o=getCache().get(String.valueOf(id));
        if(null==o){
            t=super.get(id);
            if(null!=t){
                getCache().put(String.valueOf(id),t);
            }
        }else {
            t=(T)o;
        }
        return t;
    }

    public List<T> listAll() {
        List<T> list=null;
        Object o=getCache().get(listAllKey);
        if(null==o){
            list=super.listAll();
            if(null!=list){
                getCache().put(listAllKey,list);
            }
        }else {
            list=(List<T>)o;
        }
        return list;
    }

    public void removeCache(Long id){
        getCache().remove(String.valueOf(id));
        removeAllListCache();
    }

    private void removeAllListCache(){
        getCache().remove(listAllKey);
    }

    public Cache<String,Object> getCache(){
        if(null==cache){
            cache= CacheFactory.getCache(this.getClass().getName());
        }
        return cache;
    }


    //------------在修改信息后清除缓存--------------------------------------
    public Long insert(T t) {
        Long id= getBaseDao().insert(t);
        removeAllListCache();
        return id;
    }

    public Long update(T t) {
        Long id= getBaseDao().update(t);
        removeCache(id);
        return id;
    }


    public Long delete(Long id){
        Long resultId= getBaseDao().delete(id);
        removeCache(id);
        return resultId;
    }
}
