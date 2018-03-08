package com.shihu.service.impl;

import com.shihu.base.BaseCacheServiceImpl;
import com.shihu.entity.PagePrompt;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.CarType;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.mybatis.dao.CarDao;
import com.shihu.mybatis.dao.CarTypeDao;
import com.shihu.service.CarTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarTypeServiceImpl extends BaseCacheServiceImpl<CarTypeVO> implements CarTypeService {
    @Autowired
    private CarTypeDao carTypeDao;
    @Autowired
    private CarDao carDao;



    public CarType getCarTypeById(Long carTypeId) {
        CarType carType=null;
        CarTypeVO carTypeVO=carTypeDao.get(carTypeId);
        if(null!=carTypeVO){
            List<CarVO> carVOList=carDao.listByCarTypeId(carTypeId);
            carType=new CarType(carTypeVO,carVOList);
        }
        return carType;
    }

    public Long delete(Long id){
        Long oneId=carDao.getOneIdByCarTypeId(id);
        if(null!=oneId){
            throw new PagePromptException(PagePrompt.CARTYPE_NOT_EMPTY);
        }else {
            return super.delete(id);
        }
    }

    public Long insert(CarTypeVO carTypeVO) {
        Long oneId=carTypeDao.getOneIdByName(carTypeVO.getName());
        if(null!=oneId){
            throw new PagePromptException(PagePrompt.ADD_INFO_REPART);
        }else {
            return super.insert(carTypeVO);
        }
    }

}
