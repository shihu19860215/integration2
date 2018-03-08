package com.shihu.model.common.VO;

import com.shihu.base.BaseCommonVO;

import java.util.Date;

public class LogVO extends BaseCommonVO {
    private static final long serialVersionUID = 6815982882354804503L;
    private String info;
    private Date createTime;

    public String getInfo() {
        return info;
    }

    public LogVO setInfo(String info) {
        this.info = info;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public LogVO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }
}
