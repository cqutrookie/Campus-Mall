package com.lxyup.SaveMoney.service.impl;

import com.lxyup.SaveMoney.mapper.DelectCartMapper;
import com.lxyup.SaveMoney.mapper.GetBookMapper;
import com.lxyup.SaveMoney.mapper.InsertPlanMapper;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.ShoppingCart;
import com.lxyup.SaveMoney.service.ShoppingcartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class ShoppingcartImpl implements ShoppingcartService {

    @Resource
    private InsertPlanMapper insertPlanMapper;
    @Resource
    private DelectCartMapper delectCartMapper;
    @Resource
    private GetBookMapper getBookMapper;
    @Override
    public String addcart(ShoppingCart shoppingCart) {
            String CODE="";
            //请求锁
            int flag = 0;
           //判断订阅者id和卖家id是否一致
            int merchantid = shoppingCart.getMerchantid();
            int subscriberid = shoppingCart.getSubscriberid();
            if (merchantid == subscriberid){
                //不能添加自己出售的商品
                CODE="201";
                //锁住
                flag = 1;
            }
            //根据订阅者id查阅订阅者所有订阅的商品id
            ArrayList<Integer> commoditiesid = new ArrayList<>();
            commoditiesid = getBookMapper.getCommoditiesId(subscriberid);
            for (int i = 0;i<commoditiesid.size();i++){
                //如果有添加
                if (shoppingCart.getCommodityid() == commoditiesid.get(i)){
                    CODE = "202";
                    flag = 1;
                }
            }

            //调用添加接口
            if (flag==0){
                insertPlanMapper.insertcart(shoppingCart);
                CODE = "200";
            }

           return CODE;
    }

    @Override
    public ArrayList<Commodity> getShoppingCartById(int userid) {
        ArrayList<ShoppingCart> shoppingCarts = new ArrayList<>();
        //获取购物车所有信息
        shoppingCarts = getBookMapper.getShoppingCartById(userid);
        ArrayList<Commodity> commodities = new ArrayList<>();
        //根据购物车所有信息的商品id返回所有的商品
        for (int i = 0 ; i < shoppingCarts.size() ; i++){
            int commodityid = shoppingCarts.get(i).getCommodityid();
            Commodity commodity = getBookMapper.getCommodityById(commodityid);
            commodities.add(commodity);
        }
        return commodities;

    }


    @Override
    public void clearCart(int userid) {
        //删除用户购物车全部信息
        delectCartMapper.deleteCart(userid);
    }

    @Override
    public void deleteOne(int userid, int commodityid) {
        delectCartMapper.deleteOne(userid,commodityid);
    }
}
