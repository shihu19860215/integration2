package com.shihu.service.impl;

import com.shihu.base.BasePageCacheServiceImpl;
import com.shihu.base.Page;
import com.shihu.entity.PagePrompt;
import com.shihu.exception.PagePromptException;
import com.shihu.model.common.Order;
import com.shihu.model.common.OrderProduct;
import com.shihu.model.common.VO.*;
import com.shihu.mybatis.dao.OrderDao;
import com.shihu.mybatis.dao.OrderProductDao;
import com.shihu.service.CustomerService;
import com.shihu.service.OrderService;
import com.shihu.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl extends BasePageCacheServiceImpl<OrderVO> implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderProductDao orderProductDao;
    @Autowired
    private ProductService productService;



    protected int getCount(Page<OrderVO> page) {
        return orderDao.count(page);
    }

    @Transactional
    public void addOrder(Order order) throws PagePromptException {
        if(null!=order.getCustomerVO()
                &&null!=order.getCustomerVO().getId()
                &&(null!=order.getOrderProductList()&&order.getOrderProductList().size()>0)){
            List<OrderProduct> orderProductList=order.getOrderProductList();
            Boolean retreat=false;
            for(int i=0;i<orderProductList.size();i++){
                OrderProduct orderProduct=orderProductList.get(i);
                if(!orderProduct.isSell()){
                    retreat=true;
                    break;
                }
            }
            order.setRetreat(retreat);
            OrderVO orderVO=new OrderVO(order);
            orderDao.insert(orderVO);

            for(int i=0;i<orderProductList.size();i++){
                OrderProduct orderProduct=orderProductList.get(i);
                ProductVO productVO=productService.get(orderProduct.getProduct().getId());
                if(!productVO.isDisplay()){
                    throw new PagePromptException(PagePrompt.ADD_ORDER_ERROR_PRODCUT_INVAILD);
                }
                if(retreat){
                    productVO.setNum(productVO.getNum()+ orderProduct.getNum());
                }else {
                    productVO.setNum(productVO.getNum() - orderProduct.getNum());
                }
                productService.updateNum(productVO);
                OrderProductVO orderProductVO=new OrderProductVO(orderProduct,orderVO.getId());
                orderProductDao.insert(orderProductVO);
            }
            getPageCache().clear();
        }
    }

    public Order getOrderById(Long id) {
        OrderVO orderVO=super.get(id);
        CustomerVO customerVO=customerService.get(orderVO.getCustomerId());
        List<OrderProductAndProductVO> orderProductAndProductVOList= orderProductDao.getOrderProductAndProductVOListByOrderId(id);
        Order order=new Order(orderVO,customerVO,orderProductAndProductVOList);
        return order;
    }


    public List<Order> listOrder(Page<OrderVO> page) {
        List<OrderVO> orderVOList=super.list(page);
        return null;
    }
}
