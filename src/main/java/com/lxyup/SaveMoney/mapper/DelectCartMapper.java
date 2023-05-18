package com.lxyup.SaveMoney.mapper;

public interface DelectCartMapper {
    /**
     * 删除用户购物车所有内容
     */
    public void deleteCart(int userid);

    public void deleteOne(int userid , int commodityid);
}
