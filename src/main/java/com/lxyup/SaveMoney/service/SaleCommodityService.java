package com.lxyup.SaveMoney.service;

import org.springframework.web.multipart.MultipartFile;

public interface SaleCommodityService {
    public String saleCommodity(MultipartFile file , String commodityName,  String commodityDes , int price, int userid);
}
