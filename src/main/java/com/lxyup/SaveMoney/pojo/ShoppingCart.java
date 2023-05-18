package com.lxyup.SaveMoney.pojo;

//购物车类
public class ShoppingCart {
    private int cartid;
    private int subscriberid;
    private int merchantid;
    private int commodityid;

    public int getCartid() {
        return cartid;
    }

    public void setCartid(int cartid) {
        this.cartid = cartid;
    }

    public int getSubscriberid() {
        return subscriberid;
    }

    public ShoppingCart(int cartid, int subscriberid, int merchantid, int commodityid) {
        this.cartid = cartid;
        this.subscriberid = subscriberid;
        this.merchantid = merchantid;
        this.commodityid = commodityid;
    }

    public void setSubscriberid(int subscriberid) {
        this.subscriberid = subscriberid;
    }

    public int getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(int merchantid) {
        this.merchantid = merchantid;
    }

    public int getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(int commodityid) {
        this.commodityid = commodityid;
    }
    public ShoppingCart(){


    }
}
