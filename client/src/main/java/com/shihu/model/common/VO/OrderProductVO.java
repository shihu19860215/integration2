package com.shihu.model.common.VO;

import com.shihu.model.common.OrderProduct;

import java.io.Serializable;

public class OrderProductVO implements Serializable {
    private Long id;
    private Long orderId;//订单ID
    private Long productId;//商品id
    private int num;//订单商品数量
    private Integer price;//单价
    private boolean sell;//是卖还是退货

    public OrderProductVO() {
    }
    public OrderProductVO(OrderProduct orderProduct,Long orderId) {
        this.orderId=orderId;
        this.productId=orderProduct.getProduct().getId();
        this.num=orderProduct.getNum();
        this.price=orderProduct.getPrice();
        this.sell=orderProduct.isSell();
    }

    public Long getId() {
        return id;
    }

    public OrderProductVO setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderProductVO setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public int getNum() {
        return num;
    }

    public OrderProductVO setNum(int num) {
        this.num = num;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OrderProductVO setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public boolean isSell() {
        return sell;
    }

    public OrderProductVO setSell(boolean sell) {
        this.sell = sell;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public OrderProductVO setProductId(Long productId) {
        this.productId = productId;
        return this;
    }
}
