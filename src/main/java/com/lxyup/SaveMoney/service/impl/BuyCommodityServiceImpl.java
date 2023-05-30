package com.lxyup.SaveMoney.service.impl;

import com.lxyup.Login.mapper.UserLoginMapper;
import com.lxyup.SaveMoney.mapper.GetBookMapper;
import com.lxyup.SaveMoney.mapper.InsertPlanMapper;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.Transaction;
import com.lxyup.SaveMoney.service.BuyCommodityService;
import com.lxyup.SaveMoney.service.ShoppingcartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class BuyCommodityServiceImpl implements BuyCommodityService {

    @Resource
    private ShoppingcartService shoppingcartService;
    @Resource
    private GetBookMapper getBookMapper;
    @Resource
    private UserLoginMapper userLoginMapper;
    @Resource
    private InsertPlanMapper insertPlanMapper;
    public int computeAllCommodities(int[] ids){
        int sum = 0;
        for (int i = 0;i<ids.length;i++){
            Commodity commodity = getBookMapper.getCommodityById(ids[i]);
            sum+=commodity.getCommodityprice();
        }
        return sum;
    }

    @Override
    public Map<String,Object> buyCommodity(int[] ids, int userid) {
        Map<String,Object> map = new HashMap<>();
        int allPrice = computeAllCommodities(ids);
        int userMoney = userLoginMapper.getmoney(userid);
        //用户金额不足返回201
        if (allPrice>userMoney){
            map.put("CODE","201");
            return map;
        }
        //用户进行购买操作
        else{
            for (int i = 0;i<ids.length;i++){
                //获取商品信息
                Commodity commodity = getBookMapper.getCommodityById(ids[i]);
                //商品被用户已经买了
                if (commodity.getStatus()==1){
                    map.put("CODE","202");
                    map.put("commodityname",commodity.getCommodityname());
                    return map;
                }
                if (commodity.getStatus()==2){
                    map.put("CODE","204");
                    map.put("commodityname",commodity.getCommodityname());
                    return map;
                }
                int commodityid = commodity.getCommodityid();
                int merchantid = commodity.getMerchantid();
                Date creattime = new Date();
                int subscriberid = userid;
                //添加操作
                Transaction transaction = new Transaction();
                transaction.setCommodityid(commodityid);
                transaction.setCreattime(creattime);
                transaction.setMerchantid(merchantid);
                transaction.setSubscriber(userid);
                insertPlanMapper.insertTransaction(transaction);

                //将商品的status置为1
                insertPlanMapper.updateCommodityStatus(ids[i]);
            }
            //扣除用户金额
            insertPlanMapper.updateUserMoney(userid,allPrice);

            //清空购物车
            shoppingcartService.clearCart(userid);
            map.put("CODE","200");
        }
        return map;
    }

    @Override
    public String confirmCom(int userid, int commodityid, int merchantid) {
        insertPlanMapper.confirmCom(userid,commodityid);
        //给买家加钱

        Commodity commodity = getBookMapper.getCommodityById(commodityid);
        int commodityprice = commodity.getCommodityprice();

        insertPlanMapper.addMerchantMoney(merchantid,commodityprice);

        return "200";
    }
}
