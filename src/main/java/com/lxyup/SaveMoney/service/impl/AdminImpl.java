package com.lxyup.SaveMoney.service.impl;

import com.lxyup.Login.mapper.UserLoginMapper;
import com.lxyup.Login.pojo.User;
import com.lxyup.SaveMoney.mapper.GetBookMapper;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.Transaction;
import com.lxyup.SaveMoney.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminImpl implements AdminService {
    @Resource
    UserLoginMapper userLoginMapper;
    @Resource
    GetBookMapper getBookMapper;
    @Override
    public Map<String, Object> backMessage(String inputstr) {
        Map<String,Object> res = new HashMap<>();
        //检测头部命令
        String[] strings = inputstr.split(" ");
        switch (strings[0]){
            case "check":
                res = check(strings);
                break;
            case "prohibit":
                res = prohibit(strings);
                break;
            case "release":
                res = release(strings);
                break;
            case "delete":
                res = delete(strings);
                break;
            case "grounding":
                res = grounding(strings);
                break;
            case "send":
                res = send(strings);
                break;
            case "recharge":
                res = recharge(strings);
                break;
            default:
                res.put("CODE","201");
                break;
        }

        return res;
    }


    /**
     * 用户充值
     * @param checkmess
     * @return
     */
    public Map<String,Object> recharge(String[] checkmess){
        Map<String,Object> res = new HashMap<>();
        int temp = 0;
        if (checkmess.length!=5){
            res.put("CODE","201");
            return res;
        }
        else if (!checkmess[1].equals("user")){
            res.put("CODE","201");
            return res;
        }
        else if (!checkmess[3].equals("money")){
            res.put("CODE","201");
            return res;
        }
        else{
            String username = checkmess[2];
            String m = checkmess[4];
            int money = Integer.parseInt(m);
            String userid = userLoginMapper.checkUsername(username);

            if (userid==null){
                res.put("CODE","200");
                res.put("backMessage","未找到用户");
                return res;
            }
            else{
                    User user = userLoginMapper.adminCheckUsermessage(Integer.parseInt(username));
                    temp = user.getMoney();
                    getBookMapper.recharge(username,money);
                    User usernow = userLoginMapper.adminCheckUsermessage(Integer.parseInt(username));
                    res.put("CODE","200");
                    res.put("backMessage","充值成功,用户原有金额为:"+temp+"  用户现有金额为:"+usernow.getMoney());

            }
        }
        return res;
    }

    /**
     * 已发货
     * @param checkmess
     * @return
     */
    public Map<String,Object> send(String[] checkmess){
        Map<String,Object> res = new HashMap<>();
        if (checkmess.length!=3){
            res.put("CODE","201");
            return res;
        }
        else if (!checkmess[1].equals("transaction")){
            res.put("CODE","201");
            return res;
        }
        else{
            String id = checkmess[2];
            int transactionid = Integer.parseInt(id);
            Transaction transaction = getBookMapper.adminCheckTransactionByid(transactionid);
            if (transaction==null){
                res.put("CODE","200");
                res.put("backMessage","未找到该交易单");

                return res;
            }
            else{
                if (transaction.getStatus() == 1){
                    res.put("CODE","200");
                    res.put("backMessage","已经发货，不可重复操作");
                    return res;
                }
                if (transaction.getStatus() == 2){
                    res.put("CODE","200");
                    res.put("backMessage","已经确认收货不可发货");
                    return res;
                }
                else{
                    getBookMapper.adminSendTransaction(transactionid);
                    res.put("CODE","200");
                    res.put("backMessage","成功发货");
                }
            }
        }
        return res;
    }

    /**
     * 商品恢复
     * @param checkmess
     * @return
     */
    public Map<String,Object> grounding(String[] checkmess){
        Map<String,Object> res = new HashMap<>();
        if (checkmess.length!=3){
            res.put("CODE","201");
            return res;
        }
        else if (!checkmess[1].equals("commodity")){
            res.put("CODE","201");
            return res;
        }
        else{
            String id = checkmess[2];
            int commodityid = Integer.parseInt(id);
            Commodity commodity = getBookMapper.getCommodityById(commodityid);
            if (commodity==null){
                res.put("CODE","200");
                res.put("backMessage","未找到商品");

                return res;
            }
            else{
                if (commodity.getStatus() == 1){
                    res.put("CODE","200");
                    res.put("backMessage","商品已经被购买，不可恢复");
                    return res;
                }
                else{
                    getBookMapper.adminGroundingCommodity(commodityid);
                    res.put("CODE","200");
                    res.put("backMessage","恢复商品成功");
                }
            }
        }
        return res;
    }
    /**
     * 删除商品功能
     * @param checkmess
     * @return
     */
    public Map<String,Object> delete(String[] checkmess){
        Map<String,Object> res = new HashMap<>();
        if (checkmess.length!=3){
            res.put("CODE","201");
            return res;
        }
        else if (!checkmess[1].equals("commodity")){
            res.put("CODE","201");
            return res;
        }
        else{
            String id = checkmess[2];
            int commodityid = Integer.parseInt(id);
            Commodity commodity = getBookMapper.getCommodityById(commodityid);
            if (commodity==null){
                res.put("CODE","200");
                res.put("backMessage","未找到商品");

                return res;
            }
            else{
                if (commodity.getStatus() == 1){
                    res.put("CODE","200");
                    res.put("backMessage","商品已经被购买，不可下架");
                    return res;
                }
                else{
                    getBookMapper.adminDeleteCommodity(commodityid);
                    res.put("CODE","200");
                    res.put("backMessage","商品已经被下架");
                }
            }
        }
        return res;
    }

    /**
     * 释放用户
     * @param checkmess
     * @return
     */
    public Map<String,Object> release(String[] checkmess){
        Map<String,Object> res = new HashMap<>();
        if (checkmess.length!=3){
            res.put("CODE","201");
            return res;
        }
        else{
            if (!checkmess[1].equals("user")){
                res.put("CODE","201");
                return res;
            }

            else{
                String id = checkmess[2];
                int userid = Integer.parseInt(id);
                userLoginMapper.adminReleaseUser(userid);
                res.put("CODE","200");
                res.put("backMessage","用户已经被释放");
            }
        }
        return res;
    }

    /**
     * 禁止用户登录的逻辑
     * @param checkmess
     * @return
     */
    public Map<String,Object> prohibit(String[] checkmess){
        Map<String,Object> res = new HashMap<>();
        if (checkmess.length<3){
            res.put("CODE","201");
            return res;
        }
        else{
            if (!checkmess[1].equals("user")){
                res.put("CODE","201");
                return res;
            }

            else{
                String id = checkmess[2];
                int userid = Integer.parseInt(id);
                userLoginMapper.adminProhibitUser(userid);
                res.put("CODE","200");
                res.put("backMessage","用户已经被禁用");
            }
        }
        return res;
    }

    /**
     * 查询用户已经商品的命令逻辑
     * @param checkmess
     * @return
     */
    public Map<String,Object> check(String[] checkmess){
        Map<String,Object> res = new HashMap<>();
        if (checkmess[1].equals("alluser")){
            if (checkmess.length>2){
                res.put("CODE","201");
                return res;
            }
            else{
                ArrayList<User> users = getBookMapper.adminGetAllUsers();
                String backMessage = "";
                for (int i = 0;i<users.size();i++){
                    backMessage += "用户id为:"+users.get(i).getId()+"  用户账号为:"+users.get(i).getUsername()+"  用户密码为:"+users.get(i).getPassword()+"  用户名为:"+users.get(i).getName()+"  用户余额为:"+users.get(i).getMoney()+"  用户收货地址为:"+users.get(i).getAddress()+"  用户注册时间为:"+users.get(i).getRegistertime()+" 用户状态:"+getUserStatus(users.get(i).getStatus())+"\n\n";
                }
                res.put("CODE","200");
                res.put("backMessage",backMessage);
                return res;
            }
        }
        else if(checkmess[1].equals("user")){
            if (checkmess.length<3){
                res.put("CODE","201");
                return res;
            }
            String name = checkmess[2];
            //获取要查看的用户账号
            int username = Integer.parseInt(name);
            User user = userLoginMapper.adminCheckUsermessage(username);
            if (user == null){
                res.put("CODE","200");
                res.put("backMessage","未找到要查询的用户信息");
                return res;
            }
            else{
                String userMessage = "用户id为:"+user.getId()+"  用户账号为:"+user.getUsername()+"  用户密码为:"+user.getPassword()+"  用户名为:"+user.getName()+"  用户余额为:"+user.getMoney()+"  用户收货地址为:"+user.getAddress()+"  用户注册时间为:"+user.getRegistertime()+" 用户状态:"+getUserStatus(user.getStatus())+"\n";
                //获取用户发售的商品
                ArrayList<Commodity> commodities = getBookMapper.adminCheckUserCommodity(Integer.parseInt(user.getId()));
                String commoditiesMessage = "";
                String transactionsMessage = "";
                for (int i = 0;i<commodities.size();i++){
                    commoditiesMessage += "商品编号为:"+commodities.get(i).getCommodityid()+"  商品名字:"+commodities.get(i).getCommodityname()+"  商品描述:"+commodities.get(i).getCommoditydes()+"  商品价格:"+commodities.get(i).getCommodityprice()+"  商品创建时间:"+commodities.get(i).getCreattime()+"  商品状态:"+getStatus(commodities.get(i).getStatus())+"\n";
                }
                //获取用户交易信息
                ArrayList<Transaction> transactions = getBookMapper.getOrder(Integer.parseInt(user.getId()));
                for (int i = 0;i<transactions.size();i++){
                    transactionsMessage += "交易编号为:"+transactions.get(i).getTransactionid()+"  商品编号:"+transactions.get(i).getCommodityid()+"  卖家id:"+transactions.get(i).getMerchantid()+"  交易日期:"+transactions.get(i).getCreattime()+"  交易状态:"+getStatusTran(transactions.get(i).getStatus())+"\n";
                }

                res.put("CODE","200");
                String backMessage = "用户信息："+"\n"+userMessage+"\n"+"用户发售商品信息为:"+"\n"+commoditiesMessage+"\n"+"用户交易信息"+"\n"+transactionsMessage+"\n";
                res.put("backMessage",backMessage);
            }


        }
        else if (checkmess[1].equals("commodity")){
            if (checkmess.length<3){
                res.put("CODE","201");
                return res;
            }
            //查询商品
             String backMessage = "";
             String commodityid = checkmess[2];

             if (commodityid.equals("all")){
                 //返回所有的商品信息
                 ArrayList<Commodity> commodities = getBookMapper.getAllCommodity();
                 for (int i = 0;i<commodities.size();i++){
                     backMessage+="商品编号为:"+commodities.get(i).getCommodityid()+" 商品名字为:"+commodities.get(i).getCommodityname()+" 商品描述为:"+commodities.get(i).getCommoditydes()+" 商品价格为:"+commodities.get(i).getCommodityname()+" 商品创建时间:"+commodities.get(i).getCreattime()+" 发售者id为:"+commodities.get(i).getMerchantid()+" 商品状态为:"+getStatus(commodities.get(i).getStatus())+"\n\n";
                 }
                 res.put("CODE","200");
                 res.put("backMessage",backMessage);
                 return res;
             }
             else{

                 Commodity commodity = getBookMapper.getCommodityById(Integer.parseInt(commodityid));
                 if (commodity == null){
                     res.put("CODE","200");
                     res.put("backMessage","未找到该商品信息");
                     return res;
                 }
                 backMessage +="商品编号为:"+commodity.getCommodityid()+" 商品名字为:"+commodity.getCommodityname()+" 商品描述为:"+commodity.getCommoditydes()+" 商品价格为:"+commodity.getCommodityname()+" 商品创建时间:"+commodity.getCreattime()+" 发售者id为:"+commodity.getMerchantid()+" 商品状态为:"+getStatus(commodity.getStatus())+"\n";
                 res.put("CODE","200");
                 res.put("backMessage",backMessage);
             }
        }
        else if (checkmess[1].equals("transaction")){
            if (checkmess.length>2){
                res.put("CODE","201");
                return res;
            }
            else{
                ArrayList<Transaction> transactions = getBookMapper.adminCheckTransaction();
                String backMessage = "";
                for (int i = 0 ; i<transactions.size();i++){
                    //获取买家id查询送货地址
                    int subscriberid = transactions.get(i).getSubscriber();
                    String address = userLoginMapper.adminChechUserAddress(subscriberid);
                    backMessage += "交易单id:"+transactions.get(i).getTransactionid()+" 卖家id:"+transactions.get(i).getMerchantid()+" 买家收货地址:"+address+" 商品id:"+transactions.get(i).getCommodityid()+" 商品状态:"+getStatusTran(transactions.get(i).getStatus())+"\n\n";

                }
                res.put("CODE","200");
                res.put("backMessage",backMessage);
            }
        }
        else {
            res.put("CODE","201");
            return res;
        }
        return res;
    }

    public String getUserStatus(int status){
        if (status == 0){
            return "正常";
        }
        else
        {
            return "被禁用";
        }
    }
    public String getStatus(int status){
        if (status == 0){
            return "出售中";
        }
        else if (status == 1)
        {
            return "已被购买";
        }
        else if (status==2){
            return "商品已经被下架";
        }
        return "";
    }
    public String getStatusTran(int status){
        if (status == 0){
            return "还未发货";
        }
        else if (status == 1)
        {
            return "已经发货";
        }
        else if (status == 2){
            return "用户已经确认收货";
        }
        return "";
    }

}
