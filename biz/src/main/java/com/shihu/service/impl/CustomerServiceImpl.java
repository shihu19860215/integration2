package com.shihu.service.impl;

import com.shihu.base.BaseCacheServiceImpl;
import com.shihu.base.BasePageCacheServiceImpl;
import com.shihu.base.Page;
import com.shihu.entity.PagePrompt;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.mybatis.dao.CarDao;
import com.shihu.mybatis.dao.CarTypeDao;
import com.shihu.mybatis.dao.CustomerDao;
import com.shihu.service.CarTypeService;
import com.shihu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends BasePageCacheServiceImpl<CustomerVO> implements CustomerService {
    @Autowired
    private CustomerDao customerDao;



    protected int getCount(Page<CustomerVO> page) {
        return customerDao.count();
    }

    public List<CustomerVO> getCustomerVOListLikeNameOrTel(String customerNameOrTel) {
        return customerDao.getCustomerVOListLikeNameOrTel("%"+customerNameOrTel+"%");
    }
}
