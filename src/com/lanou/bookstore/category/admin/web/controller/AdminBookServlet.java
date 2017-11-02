package com.lanou.bookstore.category.admin.web.controller;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
@WebServlet("/AdminBookServlet")
public class AdminBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    // 查询所有图书
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
        return "f:/adminjsps/admin/book/list.jsp";
    }

    // 加载图书
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("allCTG", categories);
        String bid = request.getParameter("bid");
        Book book = bookService.load(bid);
        request.setAttribute("book", book);
        return "f:/adminjsps/admin/book/desc.jsp";
    }

    // 加载所有分类
    public String addPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("allCTG", categories);
        return "f:/adminjsps/admin/book/add.jsp";
    }

    // 删除图书
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        bookService.del(bid);
        findAll(request, response);
        request.setAttribute("msg", "删除成功");
        return "f:/adminjsps/msg.jsp";
    }

    // 回收站
    public String recover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.recover();
        request.setAttribute("books", books);
        return "f:/adminjsps/admin/book/recover.jsp";
    }

    // 编辑图书
    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        Book book = CommonUtils.toBean(request.getParameterMap(), Book.class);
        String price = book.getPrice();
        // 去除字符串中的元
        String pr = price.substring(0, price.length() - 1);
        book.setPrice(pr);
        bookService.edit(book);
        findAll(request, response);
        request.setAttribute("msg", "编辑成功");
        return "f:/adminjsps/msg.jsp";
    }

    // 回收图书
    public String recoverLoad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bid = request.getParameter("bid");
        bookService.recoverLoad(bid);
        request.setAttribute("msg", "回收成功");
        return "f:/adminjsps/msg.jsp";
    }
}