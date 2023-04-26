package com.lxyup.SaveMoney.service.impl;

import com.lxyup.Login.pojo.User;
import com.lxyup.SaveMoney.mapper.GetUserMapper;
import com.lxyup.SaveMoney.service.GetUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class GetUserImpl implements GetUserService {
    @Resource
    GetUserMapper getUserMapper;
    @Override
    public User getUserMes(String userid) {
        User user = new User();
        user = getUserMapper.getUser(userid);
        return user;
    }
}
