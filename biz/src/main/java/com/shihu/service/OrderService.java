package com.shihu.service;

import com.shihu.base.BaseService;
import com.shihu.base.Page;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.Order;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.model.common.VO.OrderVO;

import java.util.List;

public interface OrderService extends BaseService<OrderVO> {
    public void addOrder(Order order) throws PagePromptException;
    public int count(Page<OrderVO> page);
    public Order getOrderById(Long id);
    public List<Order> listOrder(Page<OrderVO> page);
}
