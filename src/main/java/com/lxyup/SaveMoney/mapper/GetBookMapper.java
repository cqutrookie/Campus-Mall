package com.lxyup.SaveMoney.mapper;

import com.lxyup.Login.pojo.User;
import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.ShoppingCart;
import com.lxyup.SaveMoney.pojo.Transaction;

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

    /**
     * 获取用户的订单
     */
    public ArrayList<Transaction> getOrder(int userid);

    /**
     * 获取用户的发售商品
     */
    public ArrayList<Commodity> adminCheckUserCommodity(int userid);

    /**
     * 获取全部用户信息
     */
    public ArrayList<User> adminGetAllUsers();

    /**
     * 商品下架
     */
    public void adminDeleteCommodity(int commodityid);

    /**
     * 商品恢复
     */
    public void adminGroundingCommodity(int commodityid);

    /**
     * 获取所有的交易信息
     */
    public ArrayList<Transaction> adminCheckTransaction();

    /**
     * 根据id查询交易单
     */
    public Transaction adminCheckTransactionByid(int transactionid);

    /**
     * 发货完成
     */
    public void adminSendTransaction(int transactionid);

    /**
     * 用户充值
     */
    public void recharge(String username , int money);
}
