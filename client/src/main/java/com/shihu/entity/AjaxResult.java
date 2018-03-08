package com.shihu.entity;

import java.io.Serializable;

public class AjaxResult implements Serializable{
    private static final long serialVersionUID = 5118104232476254383L;
    private int state;
    private Object info;

    public int getState() {
        return state;
    }

    public AjaxResult setState(int state) {
        this.state = state;
        return this;
    }

    public Object getInfo() {
        return info;
    }

    public AjaxResult setInfo(Object info) {
        this.info = info;
        return this;
    }
}
