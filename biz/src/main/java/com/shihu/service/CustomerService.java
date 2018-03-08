package com.shihu.service;

import com.shihu.base.BaseService;
import com.shihu.base.Page;
import com.shihu.model.common.VO.CustomerVO;

import java.util.List;

public interface CustomerService extends BaseService<CustomerVO> {
    public int count(Page<CustomerVO> page);
    public List<CustomerVO> getCustomerVOListLikeNameOrTel(String customerNameOrTel);
}
