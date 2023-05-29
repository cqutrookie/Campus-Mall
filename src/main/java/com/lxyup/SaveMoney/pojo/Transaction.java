package com.lxyup.SaveMoney.pojo;

import java.util.Date;

public class Transaction {
    private int transactionid;
    private int commodityid;
    private int merchantid;

    public int getTransactionid() {
        return transactionid;
    }

    public Transaction(int transactionid, int commodityid, int merchantid, int subscriber, Date creattime, int status) {
        this.transactionid = transactionid;
        this.commodityid = commodityid;
        this.merchantid = merchantid;
        this.subscriberid = subscriberid;
        this.creattime = creattime;
        this.status = status;
    }

    public void setTransactionid(int transactionid) {
        this.transactionid = transactionid;
    }

    public int getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(int commodityid) {
        this.commodityid = commodityid;
    }

    public int getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(int merchantid) {
        this.merchantid = merchantid;
    }

    public int getSubscriber() {
        return subscriberid;
    }

    public void setSubscriber(int subscriber) {
        this.subscriberid = subscriber;
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

    private int subscriberid;
    private Date creattime;
    private int status;

    public Transaction(){

    }
}
