package com.lxyup.SaveMoney.service;

import com.github.pagehelper.PageInfo;
import com.lxyup.SaveMoney.pojo.Book;

import java.util.ArrayList;

public interface GetBookService {
    /**
     * 获取计划列表
     * @return
     */
    public ArrayList<Book> getAllBook();

    /**
     * 获取计划分页信息
     */
    public PageInfo<Book> getBookPage(Integer pageNum);

}
