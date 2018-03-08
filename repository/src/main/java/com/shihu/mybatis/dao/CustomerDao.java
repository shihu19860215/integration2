package com.shihu.mybatis.dao;

import com.shihu.base.BaseDao;
import com.shihu.base.Page;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.CustomerVO;

import java.util.List;

public interface CustomerDao extends BaseDao<CustomerVO> {
    public int count();
    public List<CustomerVO> getCustomerVOListLikeNameOrTel(String customerNameOrTel);
}
