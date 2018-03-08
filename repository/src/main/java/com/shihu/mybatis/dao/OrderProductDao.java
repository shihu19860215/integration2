package com.shihu.mybatis.dao;

import com.shihu.base.BaseDao;
import com.shihu.model.common.VO.OrderProductAndProductVO;
import com.shihu.model.common.VO.OrderProductVO;
import com.shihu.model.common.VO.OrderVO;

import java.util.List;

public interface OrderProductDao extends BaseDao<OrderProductVO> {
    public List<OrderProductAndProductVO> getOrderProductAndProductVOListByOrderId(Long orderId);
    public void addOrderProductVO(OrderProductVO orderProductVO);
}
