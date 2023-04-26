package com.lxyup.SaveMoney.mapper;

import com.lxyup.SaveMoney.pojo.Book;

import java.util.ArrayList;

public interface GetBookMapper {
    /**
     * 获取所有的图书
     * @return
     */
    public ArrayList<Book> getBook();

    /**
     * 根据图书id获取图书
     * @param planid
     */
    public Book getBookById(Integer planid);
}
