package com.lxyup.Login.pojo;

import java.util.Date;

public class User {
    private String id;
    private String username;
    private String password;
    private int money;
    private Date registertime;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getRegistertime() {
        return registertime;
    }

    public void setRegistertime(Date registertime) {
        this.registertime = registertime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String id, String username, String password,  int money, Date registertime, String name) {
        this.id = id;
        this.username = username;
        this.password = password;

        this.money = money;
        this.registertime = registertime;
        this.name = name;
    }

    public User(){

    }
}
