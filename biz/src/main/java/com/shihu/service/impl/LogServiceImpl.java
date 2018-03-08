package com.shihu.service.impl;

import com.shihu.base.BasePageCacheServiceImpl;
import com.shihu.base.Page;
import com.shihu.model.common.VO.CustomerVO;
import com.shihu.model.common.VO.LogVO;
import com.shihu.mybatis.dao.CustomerDao;
import com.shihu.mybatis.dao.LogDao;
import com.shihu.service.CustomerService;
import com.shihu.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl extends BasePageCacheServiceImpl<LogVO> implements LogService {
    @Autowired
    private LogDao logDao;


    protected int getCount(Page<LogVO> page) {
        return logDao.count(page);
    }
}
