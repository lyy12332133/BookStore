package com.lanou.bookstore.book.domain;


import java.io.Serializable;

/**
 * Created by dllo on 17/9/21.
 */
public class Book implements Serializable{
    private String bid;
    private String bname;
    private String price;
    private String author;
    private String image;
    private String cid;
    private Boolean del = false;
    private Integer sales;
    private String content;

    public Book() {
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Book(String bid, String bname, String price, String author, String image, String cid, Boolean del, Integer sales, String content) {
        this.bid = bid;
        this.bname = bname;
        this.price = price;
        this.author = author;
        this.image = image;
        this.cid = cid;
        this.del = del;
        this.sales = sales;
        this.content = content;
    }
}
