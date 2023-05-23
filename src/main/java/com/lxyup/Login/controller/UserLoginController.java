package com.lxyup.Login.controller;

import com.lxyup.Login.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
public class UserLoginController {
    @Resource
    private UserService userService;
    @RequestMapping("/login")
    public String gotoindex(){
        return "login/login";
    }

    /**
     * 检查用户登录
     * @param request
     * @return
     */
    @RequestMapping("/checkUser")
    @ResponseBody
    public Map<String,Object> checkUser(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        Map<String,Object> result = new HashMap<>();

        //用户session

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("username",username);
        httpSession.setAttribute("password",password);

        String Code = userService.checkUser(username,password);
        //存入用户id
        httpSession.setAttribute("userid",Code);
        if(Code == "00")
        {

            result.put("CODE","0");
        }
        else
        {
            //获取用户名和id 返回
            String name = userService.getname(username);
            result.put("userid",Code);
            result.put("name",name);
            result.put("CODE","1");
        }
        return result;
    }

    /**
     * 用户注册
     */
    @RequestMapping("/register")
    @ResponseBody
    public Map<String,Object> register(HttpServletRequest request){
        Map<String,Object> result = new HashMap<>();
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String CODE = userService.register(name,username,password,address);
        result.put("CODE",CODE);
        return result;
    }

}
