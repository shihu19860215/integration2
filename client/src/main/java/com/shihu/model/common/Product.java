package com.shihu.model.common;

import com.google.gson.reflect.TypeToken;
import com.shihu.base.BaseCommon;
import com.shihu.model.common.VO.CarVO;
import com.shihu.model.common.VO.ProductVO;
import com.shihu.utils.JsonUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Product extends BaseCommon {
    private static final long serialVersionUID = -7549055237374546419L;
    private String name;
    private int num;
    private String version;
    private Integer ownerprice;
    private Integer otherprice;
    private String remark;
    private List<CarVO> carVOList;
    private static Type type=new TypeToken<ArrayList<CarVO>>(){}.getType();

    public Product() {

    }
    public Product(ProductVO productVO) {
        this.id=productVO.getId();
        this.name=productVO.getName();
        this.num=productVO.getNum();
        this.version=productVO.getVersion();
        this.ownerprice=productVO.getOwnerprice();
        this.otherprice=productVO.getOtherprice();
        this.remark=productVO.getRemark();
        this.carVOList=JsonUtils.fromJson(productVO.getCarStr(),type);
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public int getNum() {
        return num;
    }

    public Product setNum(int num) {
        this.num = num;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public Product setVersion(String version) {
        this.version = version;
        return this;
    }

    public Integer getOwnerprice() {
        return ownerprice;
    }

    public Product setOwnerprice(Integer ownerprice) {
        this.ownerprice = ownerprice;
        return this;
    }

    public Integer getOtherprice() {
        return otherprice;
    }

    public Product setOtherprice(Integer otherprice) {
        this.otherprice = otherprice;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public Product setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public List<CarVO> getCarVOList() {
        return carVOList;
    }

    public void setCarVOList(String carStr) {
        if(null!=carStr&&carStr.length()>0){
            Type t=new TypeToken<ArrayList<CarVO>>(){}.getType();
            this.carVOList= JsonUtils.fromJson(carStr,t);
        }
    }
}
