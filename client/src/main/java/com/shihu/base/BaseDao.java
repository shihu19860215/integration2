package com.shihu.base;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface BaseDao<T> {
    /**
     * 获取数据
     * @param id
     * @return
     */
    public T get(Long id);

    /**
     *  插入数据
     * @param entity
     * @return
     */
    public Long insert(T entity);

    /**
     * 更新数据
     * @param entity
     * @return
     */
    public Long update(T entity);

    /**
     * 删除数据
     * @param id
     * @return
     */
    public Long delete(Long id);


    /**
     * 获取所有数据
     * @return
     */
    public List<T> listAll();


    /**
     * 分页查询数据
     * @param page
     * @return
     */
    public List<T> list(Page<T> page);
}
