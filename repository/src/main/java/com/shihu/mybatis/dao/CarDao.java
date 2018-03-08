package com.shihu.mybatis.dao;

import com.shihu.base.BaseDao;
import com.shihu.model.common.VO.CarVO;

import java.util.List;

public interface CarDao extends BaseDao<CarVO> {
    /**
     * 获取一条数据，用来判断是否为空
     * @return
     */
    public Long getOneIdByCarTypeId(Long carTypeId);
    /**
     * 通过名称检查是否存在
     * @param name
     * @return 返回查到的第一个对象ID
     */
    public Long getOneIdByName(String name);

    /**
     * 获取该车一个商品，用来查看该车有无商品
     * @param carId
     * @return
     */
    public Long getOneProductId(Long carId);

    public List<CarVO> listByCarTypeId(Long carTypeId);

}
