package com.lxyup.SaveMoney.pojo;

import java.util.Date;

public class Commodity {
    private int commodityid;
    private String commodityname;
    private String commoditydes;
    private int commodityprice;
    private Date creattime;
    private int merchantid;
    private String img;
    private int status;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getCreattime() {
        return creattime;
    }

    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Commodity(int commodityid, String commodityname, String commoditydes, int commodityprice, Date creattime, int merchantid , String img, int status) {
        this.commodityid = commodityid;
        this.commodityname = commodityname;
        this.commoditydes = commoditydes;
        this.commodityprice = commodityprice;
        this.creattime = creattime;
        this.merchantid = merchantid;
        this.img = img;
        this.status = status;
    }

    public int getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(int commodityid) {
        this.commodityid = commodityid;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getCommoditydes() {
        return commoditydes;
    }

    public void setCommoditydes(String commoditydes) {
        this.commoditydes = commoditydes;
    }

    public int getCommodityprice() {
        return commodityprice;
    }

    public void setCommodityprice(int commodityprice) {
        this.commodityprice = commodityprice;
    }

    public Date getCreatetime() {
        return creattime;
    }

    public void setCreatetime(Date creattime) {
        this.creattime = creattime;
    }

    public int getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(int merchantid) {
        this.merchantid = merchantid;
    }

    public Commodity(){

    }
}
