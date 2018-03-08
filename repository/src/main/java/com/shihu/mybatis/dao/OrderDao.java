package com.shihu.mybatis.dao;

import com.shihu.base.BaseDao;
import com.shihu.base.Page;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.model.common.VO.OrderVO;

public interface OrderDao extends BaseDao<OrderVO> {
    public int count(Page<OrderVO> page);

}
