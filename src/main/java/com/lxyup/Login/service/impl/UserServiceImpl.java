package com.lxyup.Login.service.impl;

import com.lxyup.Login.mapper.UserLoginMapper;
import com.lxyup.Login.pojo.User;
import com.lxyup.Login.service.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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

    @Override
    public String register(String name,String username, String password, String address) {
        String id = userLoginMapper.checkUsername(username);
        //如果id为空说明可以注册，否则返回‘00’表示该账号已经存在
        if (id != null){
            return "00";

        }
        else{
            User user = new User();
            user.setMoney(0);
            user.setName(name);
            user.setRegistertime(new Date());
            user.setUsername(username);
            user.setPassword(password);
            user.setAddress(address);
            userLoginMapper.register(user);
            //返回01表示注册成功
            return "01";
        }
    }

    @Override
    public String getname(String username) {
        String name = userLoginMapper.getname(username);
        return name;
    }
}
