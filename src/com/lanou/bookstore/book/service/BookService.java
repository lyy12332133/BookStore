package com.lanou.bookstore.book.service;

import com.lanou.bookstore.book.domain.Book;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface BookService {
    List<Book> findAll();

    Book load(String bid);

    List<Book> findByCategory(String cid);

    void edit(Book book);

    void del(String bid);

    void add(Book book);

    List<Book> recover();

    void recoverLoad(String bid);

    void updateSales(int sum,String bid);


    List<Book> findAllByPriceDown();

    List<Book> findAllBySales();

    List<Book> findAllByPriceUp();

    List<Book> findBySales(String cid);

    List<Book> findByPriceDown(String cid);

    List<Book> findByPriceUp(String cid);
}
