package com.lanou.bookstore.cart.domain;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/28.
 */
public class CartBean implements Serializable{
    private String cid;
    private String uid;
    private String bid;
    private String count;

    public CartBean() {
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public CartBean(String cid, String uid, String bid, String count) {
        this.cid = cid;
        this.uid = uid;
        this.bid = bid;
        this.count = count;
    }
}
