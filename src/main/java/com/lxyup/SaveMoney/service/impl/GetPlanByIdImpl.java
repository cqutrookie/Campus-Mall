package com.lxyup.SaveMoney.service.impl;

import com.lxyup.SaveMoney.mapper.GetBookMapper;
import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.service.GetPlanByIdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 根据图书id查询图书信息
 */
@Service
public class GetPlanByIdImpl implements GetPlanByIdService {
    @Resource
    private GetBookMapper getPlanMapper;
    @Override
    public Book getPlanById(Integer planid) {
        Book plan = getPlanMapper.getBookById(planid);
        return plan;
    }
}
