package com.lanou.bookstore.cart.domain;

import com.lanou.bookstore.book.domain.Book;

import java.io.Serializable;

/**
 * Created by dllo on 17/9/21.
 */
public class CartItem implements Serializable{
    private Book book;
    private String count;

    public CartItem(Book book, String count) {
        this.book = book;
        this.count = count;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public CartItem() {
    }
}
