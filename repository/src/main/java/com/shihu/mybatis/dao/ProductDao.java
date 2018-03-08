package com.shihu.mybatis.dao;

import com.shihu.base.BaseDao;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;

import java.util.List;
import java.util.Map;

public interface ProductDao extends BaseDao<ProductVO> {
    public List<ProductVO> listProductVOByCarId(Long carId);
    public List<ProductVO> searchProduct(Map<String,String> map);
    public void updateNum(ProductVO productVO);
}
