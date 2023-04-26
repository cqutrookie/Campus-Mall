package com.lxyup.Login.pojo;

public class User {
    private String id;
    private String username;
    private String password;
    private int access;

    public User(String id, String username, String password, int access) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.access = access;
    }

    public String  getId() {
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

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public User(){

    }
}
