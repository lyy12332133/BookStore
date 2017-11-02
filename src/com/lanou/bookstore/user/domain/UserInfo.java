package com.lanou.bookstore.user.domain;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/25.
 */
public class UserInfo implements Serializable{
    private String uid;
    private String name;
    private String gender;
    private String birthday;
    private String cellphone;
    private String address;

    public UserInfo() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserInfo(String uid, String name, String gender, String birthday, String cellphone, String address) {
        this.uid = uid;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.cellphone = cellphone;
        this.address = address;
    }
}
