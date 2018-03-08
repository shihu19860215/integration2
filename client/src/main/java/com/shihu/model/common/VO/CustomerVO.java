package com.shihu.model.common.VO;

import com.shihu.base.BaseCommonVO;

public class CustomerVO extends BaseCommonVO {
    private static final long serialVersionUID = -2099467716594390580L;
    private String name;
    private String telephone;
    private String telephone2;
    private String area;
    private String address;

    public String getName() {
        return name;
    }

    public CustomerVO setName(String name) {
        this.name = name;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public CustomerVO setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public String getTelephone2() {
        return telephone2;
    }

    public CustomerVO setTelephone2(String telephone2) {
        this.telephone2 = telephone2;
        return this;
    }

    public String getArea() {
        return area;
    }

    public CustomerVO setArea(String area) {
        this.area = area;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public CustomerVO setAddress(String address) {
        this.address = address;
        return this;
    }
}
