package com.lxyup.Login.mapper;

import com.lxyup.Login.pojo.User;

public interface UserLoginMapper {
    public String checkUser(String username , String password);

    public String checkAccess(String username,String password);

    public String checkUsername(String username);

    public void register (User user);

    public String getname(String username);

    public int getmoney(int userid);

    public void changePassword(int userid,String password);

    public User adminCheckUsermessage(int username);

    public void adminProhibitUser(int userid);

    public void adminReleaseUser(int userid);

    public String adminChechUserAddress(int userid);
}
