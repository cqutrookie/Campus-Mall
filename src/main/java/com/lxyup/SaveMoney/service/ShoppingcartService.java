package com.lxyup.SaveMoney.service;

import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.ShoppingCart;

import java.util.ArrayList;

public interface ShoppingcartService {
    public String addcart(ShoppingCart shoppingCart);

    public ArrayList<Commodity> getShoppingCartById(int userid);

    public void  clearCart(int userid);

    public void deleteOne(int userid , int commodityid);
}
