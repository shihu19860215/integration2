package com.shihu.base;

import java.io.Serializable;

public class BaseCommon implements Serializable {
    private static final long serialVersionUID = -6722105326587389083L;
    protected Long id;

    public Long getId() {
        return id;
    }

    public BaseCommon setId(Long id) {
        this.id = id;
        return this;
    }
}
