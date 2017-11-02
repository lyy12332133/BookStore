package com.lanou.bookstore.category.web.controller;

import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryService;
import com.lanou.bookstore.category.service.impl.CategoryServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    // 查询所有分类
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> list = categoryService.findAll();
        request.setAttribute("categorys", list);
        return "f:/jsps/left.jsp";

    }
}