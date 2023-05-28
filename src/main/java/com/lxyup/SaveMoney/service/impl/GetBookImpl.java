package com.lxyup.SaveMoney.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxyup.SaveMoney.mapper.GetBookMapper;
import com.lxyup.SaveMoney.pojo.Book;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.service.GetBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetBookImpl implements GetBookService {
    @Resource
    private GetBookMapper getPlanMapper;
    @Override
    public ArrayList<Commodity> getAllBook() {
        ArrayList<Commodity> planList = getPlanMapper.getBook();
        return planList;
    }

    @Override
    public PageInfo<Commodity> getBookPage(Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 8);

        List<Commodity> list = getPlanMapper.getBook();
        List<Commodity> commodityList = new ArrayList<>();

        //获取分页相关数据
        PageInfo<Commodity> page = new PageInfo<Commodity>(list, 5);
        return page;
        }

    @Override
    public ArrayList<Commodity> getAllCommodity() {
        ArrayList<Commodity> commodities = getPlanMapper.getAllCommodity();
        return commodities;
    }

    @Override
    public PageInfo<Commodity> getSerch(String commodityname,Integer pageNum) {
        //开启分页功能
        PageHelper.startPage(pageNum, 8);

        List<Commodity> list = getPlanMapper.getSearch(commodityname);

        //获取分页相关数据
        PageInfo<Commodity> page = new PageInfo<Commodity>(list, 5);
        return page;
    }


}
