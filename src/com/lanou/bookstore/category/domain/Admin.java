package com.lanou.bookstore.category.domain;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/22.
 */
public class Admin implements Serializable{
    private String adminname;
    private String password;
    private String sales;

    public Admin() {
    }

    public Admin(String adminname, String password, String sales) {
        this.adminname = adminname;
        this.password = password;
        this.sales = sales;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }
}
