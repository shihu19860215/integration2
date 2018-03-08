package com.shihu.service.impl;

import com.shihu.base.BaseCacheServiceImpl;
import com.shihu.entity.PagePrompt;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.Product;
import com.shihu.model.common.VO.CarProductVO;
import com.shihu.model.common.VO.CarTypeVO;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;
import com.shihu.mybatis.dao.CarDao;
import com.shihu.mybatis.dao.CarProductDao;
import com.shihu.mybatis.dao.CarTypeDao;
import com.shihu.mybatis.dao.ProductDao;
import com.shihu.service.ProductService;
import com.shihu.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServiceImpl extends BaseCacheServiceImpl<ProductVO> implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CarProductDao carProductDao;
    @Autowired
    private CarDao carDao;
    @Autowired
    private CarTypeDao carTypeDao;

    public List<Product> listProductByCarId(Long carId) {
        List<ProductVO> productVOList=productDao.listProductVOByCarId(carId);
        List<Product> productList=null;
        if(null!=productVOList){
            productList=new ArrayList<Product>();
            for(ProductVO productVO:productVOList){
                productList.add(new Product(productVO));
            }
        }
        return productList;
    }

    public Long addProduct(ProductVO productVO, List<CarVO> cars) {
        productDao.insert(productVO);
        Long id=productVO.getId();
        for(CarVO c:cars){
            carProductDao.insert(new CarProductVO(c.getId(),id));
        }
        return id;
    }
    public Long update(ProductVO productVO, Long[] carIds, Long carId) {
        List<Long> newList=new ArrayList<Long>();
        if(null!=carId){
            newList.add(carId);
        }
        if(null!=carIds){
            for(int i=0;i<carIds.length;i++){
                newList.add(carIds[i]);
            }
        }
        return this.update(productVO,newList);
    }

    public Long update(ProductVO productVO, String[] carIds) {
        List<Long> newList=new ArrayList<Long>();
        if(null!=carIds&&carIds.length>0){
            for(String str:carIds){
                newList.add(Long.valueOf(str));
            }
        }
        return this.update(productVO,newList);
    }

    private Long update(ProductVO productVO,List<Long> list){
        ProductVO productVOOld=productDao.get(productVO.getId());
        Product productOld=new Product(productVOOld);
        Set<Long> oldSet=new HashSet<Long>();
        for(CarVO carVO:productOld.getCarVOList()){
            oldSet.add(carVO.getId());
        }
        boolean infoChange=false;
        boolean carChange=false;
        if(!StringUtils.equals(productVOOld.getNum(),productVO.getNum())
                ||!StringUtils.equals(productVOOld.getOtherprice(),productVO.getOtherprice())
                ||!StringUtils.equals(productVOOld.getOwnerprice(),productVO.getOwnerprice())
                ){
            infoChange=true;
        }
        if(list.size()!=oldSet.size()||!oldSet.containsAll(list)){
            carChange=true;
        }

        if(carChange){
            carProductDao.deleteByProductId(productVO.getId());
            List<CarVO> carVOList=new ArrayList<CarVO>();
            for(Long id:list){
                carVOList.add(carDao.get(id));
                carProductDao.insert(new CarProductVO(id,productVO.getId()));
            }
            productVO.setCarStr(carVOList);
        }else{
            productVO.setCarStr(productVOOld.getCarStr());
        }
        if(infoChange||carChange){
            productDao.update(productVO);
        }
        return productVO.getId();
    }

    public Product getProductByIdWithCarTypeName(Long id) {
        ProductVO productVO=productDao.get(id);
        Product product=new Product(productVO);
        List<CarVO> carVOList=product.getCarVOList();
        for(CarVO carVO:carVOList){
            CarTypeVO carTypeVO=carTypeDao.get(carVO.getCarTypeId());
            carVO.setName(carTypeVO.getName()+":"+carVO.getName());
        }
        return product;
    }

    public int addProductNum(Long id) {
        int count;
        ProductVO productVO=productDao.get(id);
        count=productVO.getNum()+1;
        productVO.setNum(count);
        productDao.update(productVO);
        return count;
    }

    public int subProductNum(Long id) {
        int count;
        ProductVO productVO=productDao.get(id);
        count=productVO.getNum();
        if(productVO.getNum()>0){
            count--;
        }else {
            throw new PagePromptException(PagePrompt.SUB_PRODCUT_ERROR_NUM_LESS);
        }
        productVO.setNum(count);
        productDao.update(productVO);
        return count;
    }

    public void updateNum(ProductVO productVO) throws PagePromptException{
        if(productVO.getNum()<0)throw new PagePromptException(PagePrompt.ADD_ORDER_ERROR_PRODCUT_NUM_LESS);
        productDao.updateNum(productVO);
    }

    public List<Product> searchProduct(String carName, String productName, String productVersion, String productRemarks, String sort) {

        Map<String,String> map=new HashMap<String,String>();
        if(null!=carName&&carName.length()>0){
            map.put("carName","%"+carName+"%");
        }
        if(null!=productName&&productName.length()>0){
            map.put("productName","%"+productName+"%");
        }
        if(null!=productVersion&&productVersion.length()>0){
            map.put("version","%"+productVersion+"%");
        }
        if(null!=productRemarks&&productRemarks.length()>0){
            map.put("remark","%"+productRemarks+"%");
        }
        if(null!=sort&&sort.length()>0){
            map.put("sort",sort);
        }
        List<ProductVO> productVOs= productDao.searchProduct(map);
        return productListToProductVOList(productVOs);
    }
    private List<Product> productListToProductVOList(List<ProductVO> productVOs){
        List<Product> list=null;
        if(null!=productVOs&&productVOs.size()>0){
            list=new ArrayList<Product>(productVOs.size());
            for(ProductVO p:productVOs){
                list.add(new Product(p));
            }
        }
        return  list;
    }

    @Override
    public Long delete(Long id) {
        carProductDao.deleteByProductId(id);
        productDao.delete(id);
        return id;
    }
}
