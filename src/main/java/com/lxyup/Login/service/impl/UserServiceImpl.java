package com.lxyup.Login.service.impl;

import com.lxyup.Login.mapper.UserLoginMapper;
import com.lxyup.Login.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLoginMapper userLoginMapper;
    @Override
    public String checkUser(String username, String password) {
        String id = userLoginMapper.checkUser(username,password);
        System.out.println(id);
        if (id == null){
            return USER_FAULT;
        }
        else{
            //返回id
            return id;
        }
    }

    @Override
    public String checkAccess(String username, String password) {
        String access = userLoginMapper.checkAccess(username,password);
        return access;
    }
}
