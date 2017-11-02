package com.lanou.bookstore.book.service.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.dao.impl.BookDaoImpl;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class BookServiceImpl implements BookService{
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book load(String bid) {
        return bookDao.load(bid);
    }

    @Override
    public List<Book> findByCategory(String cid) {
       return bookDao.findByCategory(cid);
    }

    @Override
    public void edit(Book book) {
        bookDao.edit(book);
    }

    @Override
    public void del(String bid) {
        bookDao.del(bid);
    }

    @Override
    public void add(Book book) {
        bookDao.add(book);
    }

    @Override
    public List<Book> recover() {
        return bookDao.recover();
    }

    @Override
    public void recoverLoad(String bid) {
        bookDao.recoverLoad(bid);
    }

    @Override
    public void updateSales(int sum,String bid) {
        bookDao.updateSales(sum,bid);
    }

    @Override
    public List<Book> findAllByPriceDown() {
       return bookDao.findAllByPriceDown();
    }

    @Override
    public List<Book> findAllBySales() {
        return bookDao.findAllBySales();
    }

    @Override
    public List<Book> findAllByPriceUp() {
        return bookDao.findAllByPriceUp();
    }

    @Override
    public List<Book> findBySales(String cid) {
        return bookDao.findBySales(cid);
    }

    @Override
    public List<Book> findByPriceDown(String cid) {
        return bookDao.findByPriceDown(cid);
    }

    @Override
    public List<Book> findByPriceUp(String cid) {
        return bookDao.findByPriceUp(cid);
    }


}
