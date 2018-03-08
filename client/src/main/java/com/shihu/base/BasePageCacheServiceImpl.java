package com.shihu.base;

import com.shihu.cache.CacheFactory;
import org.apache.shiro.cache.Cache;

import java.util.List;

/**
 * 分页缓存抽象类
 * @param <T>
 */
public abstract class BasePageCacheServiceImpl<T> extends BaseCacheServiceImpl<T> {
    private Cache<String,Object> pageCache;
    private final String pageKey="page";
    private final String page_count="page_count";

    @Override
    public List<T> list(Page<T> page) {
        String key=page.toKey();
        Object o=getPageCache().get(key);
        List<T> list=null;
        if(null==o){
            list=super.list(page);
            if(null!=list){
                getPageCache().put(key,list);
            }
        }else {
            list=(List<T>)o;
        }
        return list;
    }

    protected abstract int getCount(Page<T> page);
    public int count(Page<T> page){
        String key=page_count+page.toParmKey();
        Object o=getPageCache().get(key);
        int count=0;
        if(null==o){
            count=getCount(page);
            getPageCache().put(key,count);
        }else {
            count=(Integer)o;
        }
        return count;
    }

    public Cache<String,Object> getPageCache(){
        if(null==pageCache){
            pageCache= CacheFactory.getCache(this.getClass().getName()+pageKey);
        }
        return pageCache;
    }

    //-------以下方法在修改信息后清除page缓存-----------------------------------------
    public Long insert(T t) {
        Long id=super.insert(t);
        getPageCache().clear();
        return id;
    }

    public Long update(T t) {
        Long id=super.update(t);
        getPageCache().clear();
        return id;
    }


    public Long delete(Long id){
        Long resultId= super.delete(id);
        getPageCache().clear();
        return resultId;
    }
}
