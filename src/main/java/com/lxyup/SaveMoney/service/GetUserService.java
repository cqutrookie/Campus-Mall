package com.lxyup.SaveMoney.service;

import com.lxyup.Login.pojo.User;

import java.util.Map;

public interface GetUserService {
    /**
     * 获取用户信息
     * @param userid
     * @return
     */
    public User getUserMes(String userid);

}
