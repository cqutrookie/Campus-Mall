package com.lxyup.SaveMoney.controller;

import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.ShoppingCart;
import com.lxyup.SaveMoney.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@Controller
public class WorkPlanController {
    @Resource
    private BuyCommodityService buyCommodityService;
    @Resource
    private GetPlanByIdService getPlanByIdService;
    @Resource
    private ShoppingcartService shoppingcartService;
    @Resource
    private SaleCommodityService saleCommodityService;
    /**
     * 根据前端传来的planid返回对应的plan数据
     * @return
     */
    @RequestMapping("/workplan")
    @ResponseBody
    public Map<String,Object> gotoplan(HttpServletRequest request){
        HttpSession session = request.getSession();
        String planid = request.getParameter("planid");
        Map<String,Object> result = new HashMap<>();
        if (session.getAttribute("username") == null){
            result.put("CODE","00");
            result.put("message","用户登陆过期");
            return result;
        }
        else{
            Integer id = Integer.parseInt(planid);
            Book plan = getPlanByIdService.getPlanById(id);
            result.put("CODE","01");
            result.put("message","success");
            result.put("plan",plan);
        }
        return result;
    }

    /**
     * 加入购物车
     * @param request
     * @return
     */
    @RequestMapping("/addshoppingcart")
    @ResponseBody
    public Map<String,Object> addcart(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        ShoppingCart shoppingCart = new ShoppingCart();
        HttpSession httpSession = request.getSession();
        //获取用户id
        String temp = String.valueOf(httpSession.getAttribute("userid"));
        int userid = Integer.parseInt(temp);
        //添加订阅者id
        shoppingCart.setSubscriberid(userid);
        //获取商品id
        String commodityid = request.getParameter("commodityid");
        //添加商品id
        shoppingCart.setCommodityid(Integer.parseInt(commodityid));
        //获取卖家id
        String merchantid = request.getParameter("merchantid");
        //添加卖家id
        shoppingCart.setMerchantid(Integer.parseInt(merchantid));

        String code = shoppingcartService.addcart(shoppingCart);

        result.put("CODE",code);
        return result;
    }


    /**
     * 根据用户id获取购物车的信息
     * @param request
     * @return
     */
    @RequestMapping("/getshoppingcart")
    @ResponseBody
    public Map<String,Object> getShoppingCart(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        int totalprice = 0;
        HttpSession httpSession = request.getSession();
        try{
            //获取用户id
            String temp = String.valueOf(httpSession.getAttribute("userid"));
            int userid = Integer.parseInt(temp);
            //获取用户购物车所有商品
            ArrayList<Commodity> commodities = new ArrayList<>();
            commodities = shoppingcartService.getShoppingCartById(userid);
            //根据商品列表算出总价
            for (int i = 0 ; i < commodities.size(); i++){
                    totalprice += commodities.get(i).getCommodityprice();
            }

            //存入返回数据
            result.put("CODE","200");
            result.put("commodities",commodities);
            result.put("totalprice",totalprice);
        }catch (Exception e){
            e.printStackTrace();
            result.put("CODE","201");

        }


        return result;
    }

    /**
     * 删除用户购物车全部内容
     * @param request
     * @return
     */
    @RequestMapping("/clearCart")
    @ResponseBody
    public Map<String,Object> clearCart(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        HttpSession httpSession = request.getSession();
        try{
            //获取用户id
            String temp = String.valueOf(httpSession.getAttribute("userid"));
            int userid = Integer.parseInt(temp);

            //调用删除接口
            shoppingcartService.clearCart(userid);

            //存入返回数据
            result.put("CODE","200");

        }catch (Exception e){
            e.printStackTrace();
            result.put("CODE","201");

        }


        return result;
    }



    /**
     * 删除用户指定的购物车内容
     * @param request
     * @return
     */
    @RequestMapping("/deleteone")
    @ResponseBody
    public Map<String,Object> deleteOne(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        HttpSession httpSession = request.getSession();
        try{
            //获取用户id
            String temp = String.valueOf(httpSession.getAttribute("userid"));
            int userid = Integer.parseInt(temp);
            //获取要删除的商品id
            String temp1 = request.getParameter("commodityid");
            int commodityid = Integer.parseInt(temp1);
            //调用删除接口
            shoppingcartService.deleteOne(userid,commodityid);

            //存入返回数据
            result.put("CODE","200");

        }catch (Exception e){
            e.printStackTrace();
            result.put("CODE","201");

        }


        return result;
    }

    /**
     * 用户发布商品接口
     * @param request
     * @return
     */
    @RequestMapping("/saleCommodity")
    @ResponseBody
    public Map<String,Object> saleCommodity(@RequestParam(value = "name") String name,
                                  @RequestParam(value = "description") String description,
                                  @RequestParam(value = "price") String price,
                                  @RequestParam(value = "file") MultipartFile file,
                                  HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        HttpSession httpSession = request.getSession();
        // 处理前端发送的请求
        String temp = String.valueOf(httpSession.getAttribute("userid"));
        int userid = Integer.parseInt(temp);
        int commodityPrice = Integer.parseInt(price);
        String code = saleCommodityService.saleCommodity(file, name, description, commodityPrice,userid);
        result.put("CODE", code);
        return result;
    }


    /**
     * 购物车结算
     * @param
     * @param request
     * @return
     */
    @RequestMapping("/settlementCart")
    @ResponseBody
    public Map<String,Object> settlementCart (HttpServletRequest request){
        String commodityid = request.getParameter("commodityid");
        String[] id = commodityid.split(",");
        int [] ids = new int[id.length];
        for (int i = 0; i<ids.length;i++){
            ids[i] = Integer.parseInt(id[i]);
        }
        HttpSession httpSession = request.getSession();
        String temp = String.valueOf(httpSession.getAttribute("userid"));
        int userid = Integer.parseInt(temp);
        String CODE = buyCommodityService.buyCommodity(ids,userid);
        Map<String ,Object> result = new HashMap<>();
        result.put("CODE",CODE);
        return result;

    }

}
