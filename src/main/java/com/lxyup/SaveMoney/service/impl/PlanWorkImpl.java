//package com.lxyup.SaveMoney.service.impl;
//
//import com.lxyup.Login.mapper.UserLoginMapper;
//import com.lxyup.SaveMoney.mapper.InsertPlanMapper;
//import com.lxyup.SaveMoney.pojo.Book;
//import com.lxyup.SaveMoney.service.PlanWork;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.Calendar;
//import java.util.Date;
//
//@Service
//public class PlanWorkImpl implements PlanWork {
//    @Resource
//    private InsertPlanMapper insertPlan;
//    @Resource
//    private UserLoginMapper userLoginMapper;
//    @Override
//    public String insertPlan(Book plan, String username, String password) {
//        Date startdate = new Date();
//        Date endTime = new Date();
//
//        //周计划
//        if (plan.getPlantype() == 1){
//            Calendar now = Calendar.getInstance();
//            now.setTime(startdate);
//            now.add(Calendar.DAY_OF_YEAR,10);
//            endTime = now.getTime();
//        }
//        //月计划
//        if (plan.getPlantype() == 2){
//            Calendar now = Calendar.getInstance();
//            now.setTime(startdate);
//            now.add(Calendar.MONTH,1);
//             endTime = now.getTime();
//        }
//        //年计划
//        if (plan.getPlantype() == 3){
//            Calendar now = Calendar.getInstance();
//            now.setTime(startdate);
//            now.add(Calendar.YEAR,1);
//            endTime = now.getTime();
//        }
//        plan.setPlanstarttime(startdate);
//        plan.setPlanendtime(endTime);
////        String username = request.getAttribute("username").toString();
////        String password = request.getAttribute("password").toString();
//        String id =  userLoginMapper.checkUser(username,password);
//        plan.setPlanpromoterid(id);
//        plan.setPlanstatu(0);
//        insertPlan.insertPlan(plan);
//        return "01";
//    }
//}
