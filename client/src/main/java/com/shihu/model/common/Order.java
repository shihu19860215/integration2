package com.shihu.model.common;

import com.shihu.model.common.VO.CustomerVO;
import com.shihu.model.common.VO.OrderProductAndProductVO;
import com.shihu.model.common.VO.OrderProductVO;
import com.shihu.model.common.VO.OrderVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable{
    private Long id;
    private CustomerVO customerVO;
    private String customerName;
    private List<OrderProduct> orderProductList;
    private List<OrderProductVO> orderProductVOList;
    private Integer total;
    private String productNames;
    private String remarks;
    private Boolean retreat;
    private Boolean valid;
    private String createTime;


    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public CustomerVO getCustomerVO() {
        return customerVO;
    }

    public Order setCustomerVO(CustomerVO customerVO) {
        this.customerVO = customerVO;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Order setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public List<OrderProduct> getOrderProductList() {
        return orderProductList;
    }

    public Order setOrderProductList(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
        return this;
    }

    public List<OrderProductVO> getOrderProductVOList() {
        return orderProductVOList;
    }

    public Order setOrderProductVOList(List<OrderProductVO> orderProductVOList) {
        this.orderProductVOList = orderProductVOList;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public Order setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public String getProductNames() {
        return productNames;
    }

    public Order setProductNames(String productNames) {
        this.productNames = productNames;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public Order setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Boolean getRetreat() {
        return retreat;
    }

    public Order setRetreat(Boolean retreat) {
        this.retreat = retreat;
        return this;
    }

    public Boolean getValid() {
        return valid;
    }

    public Order setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public Order setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }


    public Order() {

    }
    public Order(OrderVO orderVO, CustomerVO customerVO, List<OrderProductAndProductVO> orderProductAndProductVOList) {
        this.id=orderVO.getId();
        this.customerVO = customerVO;
        this.total = orderVO.getTotal();
        this.remarks = orderVO.getRemarks();
        List<OrderProduct> orderProductList=new ArrayList<OrderProduct>(orderProductAndProductVOList.size());
        for(int i=0;i<orderProductAndProductVOList.size();i++){
            OrderProductAndProductVO orderProductAndProductVO=orderProductAndProductVOList.get(i);
            OrderProduct orderProduct=new OrderProduct(orderProductAndProductVO);
            orderProductList.add(orderProduct);
        }
        this.orderProductList=orderProductList;
    }

}
