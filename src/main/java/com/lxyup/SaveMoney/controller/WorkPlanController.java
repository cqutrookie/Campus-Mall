package com.lxyup.SaveMoney.controller;

import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.pojo.ShoppingCart;
import com.lxyup.SaveMoney.service.GetPlanByIdService;
import com.lxyup.SaveMoney.service.PlanWork;
import com.lxyup.SaveMoney.service.ShoppingcartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WorkPlanController {
    @Resource
    private GetPlanByIdService getPlanByIdService;
    @Resource
    private ShoppingcartService shoppingcartService;
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
}
