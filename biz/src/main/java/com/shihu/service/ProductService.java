package com.shihu.service;

import com.shihu.base.BaseService;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;
import com.shihu.model.common.VO.UserVO;

import java.util.List;

public interface ProductService extends BaseService<ProductVO>{
    public List<Product> listProductByCarId(Long carId);
    public Long addProduct(ProductVO productVO,List<CarVO> cars);
    public Long update(ProductVO productVO,Long[] carIds,Long carId);
    public Long update(ProductVO productVO,String[] carIds);
    public Product getProductByIdWithCarTypeName(Long id);
    public int addProductNum(Long id);
    public int subProductNum(Long id);
    public void updateNum(ProductVO productVO) throws PagePromptException;
    public List<Product> searchProduct(String carName,String productName,String productVersion,String productRemarks,String sort);
}
