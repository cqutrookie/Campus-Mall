package com.lxyup.SaveMoney.pojo;

import java.util.Date;

/**
 * 图书实体类
 */
public class Book {
    //图书id
    private int bookid;
    //图书信息
    private String bookmessage;
    //借阅金额
    private int bookmoney;
    //图书数量
    private int booknumber;
    //图书名字
    private String bookname;
    //图书类别
    private String booktype;

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookmessage() {
        return bookmessage;
    }

    public void setBookmessage(String bookmessage) {
        this.bookmessage = bookmessage;
    }

    public Book(int bookid, String bookmessage, int bookmoney, int booknumber, String bookname, String booktype) {
        this.bookid = bookid;
        this.bookmessage = bookmessage;
        this.bookmoney = bookmoney;
        this.booknumber = booknumber;
        this.bookname = bookname;
        this.booktype = booktype;
    }

    public int getBookmoney() {
        return bookmoney;
    }

    public void setBookmoney(int bookmoney) {
        this.bookmoney = bookmoney;
    }

    public int getBooknumber() {
        return booknumber;
    }

    public void setBooknumber(int booknumber) {
        this.booknumber = booknumber;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public Book(){

    }
}