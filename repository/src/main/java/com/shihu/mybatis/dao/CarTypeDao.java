package com.shihu.mybatis.dao;

import com.shihu.base.BaseDao;
import com.shihu.model.common.VO.CarTypeVO;

public interface CarTypeDao extends BaseDao<CarTypeVO> {
    /**
     * 通过名称检查是否存在
     * @param name
     * @return 返回查到的第一个对象ID
     */
    public Long getOneIdByName(String name);
}
