package com.lxyup.Login.service;

/**
 * 用户登录接口
 */
public interface UserService {

    /**
     * 用户信息不存在
     */
    public final static String USER_FAULT = "00";

    /**
     * 用户存在
     */
    public final static String USER_RIGHT = "01";

    /**
     * 查询用户是否存在
     * @param username
     * @param password
     * @return
     */
    public String checkUser(String username,String password);

    /**
     * 查询用户权限
     * @param username
     * @param password
     * @return
     */
    public String checkAccess(String username,String password);

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
     public String register(String name ,String username,String password,String address);


    /**
     * 获取用户昵称
     * @param username
     * @return
     */
     public String getname (String username);

    /**
     * 根据用户id获取用户金额
     * @param userid
     * @return
     */
     public int getPrice (int userid);
}
