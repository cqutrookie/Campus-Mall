package com.lxyup.SaveMoney.mapper;

import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.ShoppingCart;

import java.util.ArrayList;

public interface InsertPlanMapper {
    public void insertPlan(Book plan);

    public void insertcart(ShoppingCart shoppingCart);

    public void salecommodity(Commodity commodity);
}
