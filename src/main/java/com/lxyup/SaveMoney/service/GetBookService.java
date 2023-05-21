package com.lxyup.SaveMoney.service;

import com.github.pagehelper.PageInfo;
import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.pojo.Commodity;

import java.util.ArrayList;

public interface GetBookService {
    /**
     * 获取计划列表
     * @return
     */
    public ArrayList<Commodity> getAllBook();

    /**
     * 获取计划分页信息
     */
    public PageInfo<Commodity> getBookPage(Integer pageNum);

    /**
     * 获取全部商品信息
     */
    public ArrayList<Commodity> getAllCommodity();

    /**
     * 搜索框功能
     */
    public PageInfo<Commodity> getSerch(String commodityname , Integer pageNum);
}

