package com.shihu.redis.dao;

import java.util.List;
import java.util.Set;

public interface RedisDao<K,V> {
    public V get(K k);
    public V put(K k, V v) ;
    public V remove(K k) ;
    public void clear();
    public Long size();
    public Set<K> keys(K k);
    public List<V> values(K pattern);
}
