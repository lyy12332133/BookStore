package com.lanou.bookstore.order.domain;

import com.lanou.bookstore.book.domain.Book;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/21.
 */
public class OrderItem implements Serializable{
    private String iid;
    private Integer count;
    private Double subtotal;
    private String oid;
    private String bid;
    private Book book;

    public OrderItem() {
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public OrderItem(String iid, Integer count, Double subtotal, String oid, String bid, Book book) {
        this.iid = iid;
        this.count = count;
        this.subtotal = subtotal;
        this.oid = oid;
        this.bid = bid;
        this.book = book;
    }
}
