package com.lxyup.SaveMoney.service;

import java.util.Map;

public interface BuyCommodityService {
    /**
     * 获取商品总价格
     * @param ids
     * @return
     */
    public int computeAllCommodities(int [] ids);

    /**
     *购买商品
     * @param ids
     * @param userid
     * @return
     */
    public Map<String,Object> buyCommodity(int [] ids , int userid);


    /**
     * 确认收货
     * @param userid
     * @param commodityid
     * @return
     */
    public String confirmCom (int userid , int commodityid, int merchantid);
}
