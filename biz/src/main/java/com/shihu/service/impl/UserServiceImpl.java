package com.shihu.service.impl;

import com.shihu.model.common.VO.UserVO;
import com.shihu.mybatis.dao.UserDao;
import com.shihu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;
    public UserVO getUserVOByName(String userName){
        return userDao.getUserVOByName(userName);
    }
}
