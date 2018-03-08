package com.shihu.base;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {
    public T get(Long id);
    public Long insert(T t);
    public Long update(T t);
    public List<T> listAll();
    public List<T> list(Page<T> t);
    public Long delete(Long id);
}
