package com.shihu.base;

import java.io.Serializable;

public class BaseCommonVO implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Long id;

    public Long getId() {
        return id;
    }

    public BaseCommonVO setId(Long id) {
        this.id = id;
        return this;
    }
}
