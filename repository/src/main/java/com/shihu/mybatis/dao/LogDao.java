package com.shihu.mybatis.dao;

import com.shihu.base.BaseDao;
import com.shihu.base.Page;
import com.shihu.model.common.VO.LogVO;

public interface LogDao extends BaseDao<LogVO> {
    public int count(Page<LogVO> page);
}
