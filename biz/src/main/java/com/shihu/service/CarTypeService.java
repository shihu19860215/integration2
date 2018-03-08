package com.shihu.service;

import com.shihu.base.BaseService;
import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;

import java.util.List;

public interface CarTypeService extends BaseService<CarTypeVO> {

    /**
     * 获取carType
     * @param carTypeId
     * @return
     */
    public CarType getCarTypeById(Long carTypeId);


}
