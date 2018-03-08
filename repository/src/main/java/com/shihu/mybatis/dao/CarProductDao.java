package com.shihu.mybatis.dao;

import com.shihu.base.BaseDao;
import com.shihu.model.common.VO.CarProductVO;

public interface CarProductDao extends BaseDao<CarProductVO> {
    public void deleteByProductId(Long id);
}
