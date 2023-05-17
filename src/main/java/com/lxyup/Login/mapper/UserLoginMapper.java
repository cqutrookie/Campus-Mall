package com.lxyup.Login.mapper;

import com.lxyup.Login.pojo.User;

public interface UserLoginMapper {
    public String checkUser(String username , String password);

    public String checkAccess(String username,String password);

    public String checkUsername(String username);

    public void register (User user);

}
