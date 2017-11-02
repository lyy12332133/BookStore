package com.lanou.bookstore.user.domain;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/25.
 */
public class Wallet implements Serializable{
    private String uid;
    private String balance;

    public Wallet() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public Wallet(String uid, String balance) {
        this.uid = uid;
        this.balance = balance;
    }
}
