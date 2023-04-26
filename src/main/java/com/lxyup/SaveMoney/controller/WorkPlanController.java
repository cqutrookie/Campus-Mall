package com.lxyup.SaveMoney.controller;

import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.service.GetPlanByIdService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class WorkPlanController {
    @Resource
    private GetPlanByIdService getPlanByIdService;
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
}
