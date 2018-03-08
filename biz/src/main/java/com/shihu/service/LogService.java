package com.shihu.service;

import com.shihu.base.BaseService;
import com.shihu.base.Page;
import com.shihu.model.common.VO.LogVO;

public interface LogService extends BaseService<LogVO> {
    public int count(Page<LogVO> page);
}
