package com.lxyup.SaveMoney.mapper;

import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.ShoppingCart;
import com.lxyup.SaveMoney.pojo.Transaction;

import java.util.ArrayList;
import java.util.Date;

public interface InsertPlanMapper {
    public void insertPlan(Book plan);

    public void insertcart(ShoppingCart shoppingCart);

    public void salecommodity(Commodity commodity);

    public void insertTransaction(Transaction transaction);

    public void updateCommodityStatus(int id);

    public void updateUserMoney(int id,int delmoney);

    public void confirmCom(int userid , int commodityid);

    public void addMerchantMoney(int userid , int price);
}
