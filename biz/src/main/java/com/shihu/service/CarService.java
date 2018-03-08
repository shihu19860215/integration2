package com.shihu.service;

import com.shihu.base.BaseService;
import com.shihu.model.common.VO.CarVO;

import java.util.List;

public interface CarService extends BaseService<CarVO> {
    public List<CarVO> listCarVOWithCarTypeNameIncludeStr(String str);
}
