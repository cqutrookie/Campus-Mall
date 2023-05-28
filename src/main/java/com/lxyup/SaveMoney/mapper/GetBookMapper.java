package com.lxyup.SaveMoney.mapper;

import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.ShoppingCart;

import java.util.ArrayList;

public interface GetBookMapper {
    /**
     * 获取所有的图书
     * @return
     */
    public ArrayList<Commodity> getBook();

    /**
     * 根据图书id获取图书
     * @param planid
     */
    public Book getBookById(Integer planid);

    /**
     * 获取所有商品信息
     * @return
     */
    public ArrayList<Commodity> getAllCommodity();


    /**
     * 搜索框
     * @return
     */
    public ArrayList<Commodity> getSearch(String commodityname);

    /**
     * 根据用户id查询购物车所有的商品id
     */
    public ArrayList<Integer> getCommoditiesId(int subscriberid);

    /**
     * 根据用户id查询购物车的所有信息
     */
    public ArrayList<ShoppingCart> getShoppingCartById(int userid);

    /**
     * 根据商品id查询商品信息
     */
    public Commodity getCommodityById(int commodityid);


}
