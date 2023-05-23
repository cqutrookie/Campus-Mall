package com.lxyup.SaveMoney.service.impl;

import com.lxyup.SaveMoney.mapper.GetBookMapper;
import com.lxyup.SaveMoney.mapper.InsertPlanMapper;
import com.lxyup.SaveMoney.pojo.Commodity;
import com.lxyup.SaveMoney.service.SaleCommodityService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.UUID;

@Service
public class SaleCommodityImpl implements SaleCommodityService {
    @Resource
    private InsertPlanMapper insertPlanMapper;
    @Override
    public String saleCommodity(MultipartFile file, String commodityName, String commodityDes, int price, int userid) {
        try {
            Commodity commodity = new Commodity();

            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 生成新的文件名
            String newFileName = UUID.randomUUID().toString() + suffixName;
            // 存储文件
            file.transferTo(new File("E:/vuee/src/components/shop/img/" + newFileName));

            //图片地址
            commodity.setImg(newFileName);
            //商品主人
            commodity.setMerchantid(userid);
            //商品价格
            commodity.setCommodityprice(price);
            //商品名字
            commodity.setCommodityname(commodityName);
            //商品描述
            commodity.setCommoditydes(commodityDes);
            //商品创建时间
            Date date = new Date();
            commodity.setCreatetime(date);
            //信息存入数据库
            insertPlanMapper.salecommodity(commodity);
            return "200";
        } catch (Exception e) {
            e.printStackTrace();

            return "201";
        }

    }
}
