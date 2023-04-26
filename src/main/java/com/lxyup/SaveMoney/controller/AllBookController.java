package com.lxyup.SaveMoney.controller;

import com.github.pagehelper.PageInfo;
import com.lxyup.Login.pojo.User;
import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.service.GetBookService;
import com.lxyup.SaveMoney.service.GetUserService;
import jflex.anttask.JFlexTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询图书信息界面
 */
@Controller
public class AllBookController {

    @Resource
    private GetUserService getUserService;
    @Resource
    private GetBookService getPlanService;
    @RequestMapping("/getAllPlan")
    @ResponseBody
    public Map<String,Object> getAllPlan(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null){
            result.put("CODE","00");
            result.put("message","用户登陆过期");
            return result;
        }
        //查询所有的图书信息
        ArrayList<Book> planList = getPlanService.getAllBook();
        result.put("data",planList);
        result.put("CODE","01");
        result.put("message","success");
        return result;
    }

    @RequestMapping("/getPage")
    @ResponseBody
    public Map<String,Object> getPage(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null){
            result.put("CODE","00");
            result.put("message","用户登陆过期");
            return result;
        }
        String pageNum = request.getParameter("pageNum");
        //获取员工分页信息
        PageInfo<Book> page = getPlanService.getBookPage(Integer.parseInt(pageNum));
        result.put("page",page);
        return result;
    }

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @RequestMapping("getusermessage")
    @ResponseBody
    public User getUserMessage(HttpServletRequest request){
        //获取用户信息
        HttpSession httpSession = request.getSession();
        String userid = String.valueOf(httpSession.getAttribute("userid"));
        User user = getUserService.getUserMes(userid);
        return user;
    }
}




