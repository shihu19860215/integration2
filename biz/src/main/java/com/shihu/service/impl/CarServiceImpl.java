package com.shihu.service.impl;

import com.shihu.base.BaseCacheServiceImpl;
import com.shihu.entity.PagePrompt;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.VO.CarVO;
import com.shihu.mybatis.dao.CarDao;
import com.shihu.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl extends BaseCacheServiceImpl<CarVO> implements CarService {
    private Map<Long,CarVO> idCarVOMap;
    @Autowired
    private CarDao carDao;

    /**
     * 查询是否有数据，有数据无法删除
     * @param id
     * @return
     */
    @Override
    public Long delete(Long id){
        Long oneId=carDao.getOneProductId(id);
        if(null!=oneId){
            throw new PagePromptException(PagePrompt.CARTYPE_NOT_EMPTY);
        }else {
            return super.delete(id);
        }
    }

    /**
     * 查询是否有数据 ，有数据无法插入
     * @param carVO
     * @return
     */
    @Override
    public Long insert(CarVO carVO) {
        Long oneId=carDao.getOneIdByName(carVO.getName());
        if(null!=oneId){
            throw new PagePromptException(PagePrompt.ADD_INFO_REPART);
        }else {
            return super.insert(carVO);
        }
    }

    public List<CarVO> listCarVOWithCarTypeNameIncludeStr(String str) {
        List<CarVO> list=new ArrayList<CarVO>();
        List<CarVO> carVOList=super.listAll();
        for(CarVO carVO:carVOList){
            String carName=carVO.getName();
            if(carName.indexOf(str)>=0){
                list.add(carVO);
            }
        }
        return list;
    }

}
