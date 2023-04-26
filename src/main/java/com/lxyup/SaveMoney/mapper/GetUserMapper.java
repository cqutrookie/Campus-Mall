package com.lxyup.SaveMoney.mapper;

import com.lxyup.Login.pojo.User;

import java.util.Map;

public interface GetUserMapper {
    /**
     * 获取用户信息
     * @param userid
     * @return
     */
    public User getUser(String userid);
}
