package com.shihu.model.common.VO;

import com.shihu.model.common.Order;

import java.util.Date;

public class OrderVO {
    private Long id;
    private Long  customerId;
    private String customerName;
    private Integer total;
    private String productNames;
    private String remarks;
    private Boolean retreat;//是否有退货
    private Boolean valid;//有效的
    private Date createTime;// 创建时间

    public OrderVO() {
    }

    public OrderVO(Order order) {
        this.customerId = order.getCustomerVO().getId();
        this.customerName=order.getCustomerName();
        this.total = order.getTotal();
        this.productNames=order.getProductNames();
        this.remarks = order.getRemarks();
        this.retreat=order.getRetreat();
    }



    public Long getId() {
        return id;
    }

    public OrderVO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public OrderVO setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderVO setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public OrderVO setTotal(Integer total) {
        this.total = total;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public OrderVO setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public Boolean getRetreat() {
        return retreat;
    }

    public OrderVO setRetreat(Boolean retreat) {
        this.retreat = retreat;
        return this;
    }

    public String getProductNames() {
        return productNames;
    }

    public OrderVO setProductNames(String productNames) {
        this.productNames = productNames;
        return this;
    }

    public Boolean getValid() {
        return valid;
    }

    public OrderVO setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public OrderVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
