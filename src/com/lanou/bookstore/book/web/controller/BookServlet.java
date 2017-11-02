package com.lanou.bookstore.book.web.controller;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/BookServlet")
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    // 查询所有数据
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> list = bookService.findAll();
        request.setAttribute("books", list);
        return "f:/jsps/book/list.jsp";
    }

    // 按条件排序查询
    public String findAllBy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String by = request.getParameter("by");
        if (by.equals("a")) {
            List<Book> lists = bookService.findAllBySales();
            request.setAttribute("books", lists);
        }
        if (by.equals("b")) {
            List<Book> lists = bookService.findAllByPriceDown();
            request.setAttribute("books", lists);
        }
        if (by.equals("c")) {
            List<Book> lists = bookService.findAllByPriceUp();
            request.setAttribute("books", lists);
        }
        return "f:/jsps/book/list.jsp";
    }

    // 查询单本书信息
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bid = request.getParameter("bid");
        Book book = bookService.load(bid);
        request.setAttribute("book", book);
        return "f:/jsps/book/desc.jsp";
    }

    // 分类查询书籍
    public String findByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        request.getSession().setAttribute("cid",cid);
        List<Book> byCategory = bookService.findByCategory(cid);
        request.setAttribute("books", byCategory);

        return "f:/jsps/book/orderlist.jsp";
    }

    // 用户显示总销量
    public String findSales(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.findAll();
        int sales = 0;
        for (Book book : books) {
            Integer sale = book.getSales();
            sales += sale;
        }
        request.getSession().setAttribute("sales", sales);
        return "f:/jsps/top.jsp";
    }

    // 后台显示总销量
    public String findAllSale(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Book> books = bookService.findAll();
        int sales = 0;
        for (Book book : books) {
            Integer sale = book.getSales();
            sales += sale;
        }
        request.getSession().setAttribute("sales", sales);
        return "f:/adminjsps/admin/top.jsp";
    }
    // 按条件分类查询
    public String findByOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String by = request.getParameter("by");
        String cid = (String) request.getSession().getAttribute("cid");
        if (by.equals("a")) {
            List<Book> lists = bookService.findBySales(cid);
            request.setAttribute("books", lists);
        }
        if (by.equals("b")) {
            List<Book> lists = bookService.findByPriceDown(cid);
            request.setAttribute("books", lists);
        }
        if (by.equals("c")) {
            List<Book> lists = bookService.findByPriceUp(cid);
            request.setAttribute("books", lists);
        }
        return "f:/jsps/book/orderlist.jsp";
    }
    // 分类综合查询
    public String findByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = (String) request.getSession().getAttribute("cid");
        List<Book> byCategory = bookService.findByCategory(cid);
        request.setAttribute("books", byCategory);
        return "f:/jsps/book/orderlist.jsp";
    }

}