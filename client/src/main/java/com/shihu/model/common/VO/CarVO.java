package com.shihu.model.common.VO;

import com.shihu.base.BaseCommonVO;

public class CarVO extends BaseCommonVO {
	private static final long serialVersionUID = -1057093624291777991L;
	private String name;
	private Long carTypeId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}
}
