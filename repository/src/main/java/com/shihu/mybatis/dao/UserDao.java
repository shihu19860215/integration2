package com.shihu.mybatis.dao;

import com.shihu.model.common.VO.UserVO;
import com.shihu.base.BaseDao;

public interface UserDao extends BaseDao<UserVO>{
    public UserVO getUserVOByName(String name);
}
