package com.shihu.model.common;

import com.shihu.base.BaseCommon;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;

import java.util.List;

public class CarType extends BaseCommon{
    private static final long serialVersionUID = 5823295873489573684L;
    private String name;
    private List<CarVO> carVOList;

    public CarType(CarTypeVO carTypeVO, List<CarVO> carVOList) {
        this.id=carTypeVO.getId();
        this.name=carTypeVO.getName();
        this.carVOList = carVOList;
    }

    public String getName() {
        return name;
    }

    public CarType setName(String name) {
        this.name = name;
        return this;
    }

    public List<CarVO> getCarVOList() {
        return carVOList;
    }

    public CarType setCarVOList(List<CarVO> carVOList) {
        this.carVOList = carVOList;
        return this;
    }
}
