//package com.lxyup.SaveMoney.controller;
//
//import com.lxyup.SaveMoney.pojo.Book;
//import com.lxyup.SaveMoney.service.PlanWork;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//public class SaveController {
//
//    @Resource
//    private PlanWork planWork;
//    @RequestMapping("/insertPlan")
//    @ResponseBody
//    public Map<String,Object> insertPlan(HttpServletRequest request){
//        HttpSession session = request.getSession();
//        Map<String,Object> result = new HashMap<>();
//        if (session.getAttribute("username") == null){
//            //返回01错误
//            result.put("CODE","00");
//            result.put("message","用户未登录");
//            return result;
//        }
//        String username = session.getAttribute("username").toString();
//        String password = session.getAttribute("password").toString();
//        Book plan = new Book();
//        //获取表单参数
//        String planname = request.getParameter("planname");
//        String plantype = request.getParameter("plantype");
//        String planproperties = request.getParameter("planproperties");
//        String planmoney = request.getParameter("planmoney");
//        String planmessage = request.getParameter("planmessage");
//        String planer = request.getParameter("planer");
//
//        //装填参数
//        plan.setPlaner(planer);
//        plan.setPlanmoney(Integer.parseInt(planmoney));
//        plan.setPlanmessage(planmessage);
//        plan.setPlanproperties(Integer.parseInt(planproperties));
//        plan.setPlantype(Integer.parseInt(plantype));
//        plan.setPlanname(planname);
//
//        String message = planWork.insertPlan(plan,username,password);
//        if (message.equals("00")){
//            result.put("message","提交计划失败");
//            return result;
//        }
//        else{
//            result.put("CODE","01");
//            result.put("message","提交计划成功");
//            return result;
//        }
//
//    }
//}
