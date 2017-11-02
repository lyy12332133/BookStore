package com.lanou.bookstore.category.admin.web.controller;

import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryException;
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
@WebServlet("/AdminCategoryServlet")
public class AdminCategoryServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    // 管理员登录
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminname = request.getParameter("adminname");
        String password = request.getParameter("password");
        boolean b = categoryService.login(adminname, password);
        if (!b) {
            request.setAttribute("name", adminname);
            request.setAttribute("msg", "你不是管理员, 滚边拉子去");
            request.getRequestDispatcher("adminjsps/login.jsp").forward(request, response);
        }
        Admin salesByName = categoryService.findSalesByName(adminname);
        String sales = salesByName.getSales();
        request.getSession().setAttribute("sales", sales);
        return "f:adminjsps/admin/main.jsp";
    }

    // 查看所有分类
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> all = categoryService.findAll();
        request.getSession().setAttribute("allCTG", all);
        return "f:adminjsps/admin/category/list.jsp";
    }

    // 添加分类
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        if (category.getCname().trim().length() == 0){
            request.setAttribute("error","输入的分类名不能为空");
            request.getRequestDispatcher("adminjsps/admin/category/add.jsp").forward(request,response);
        }else {
            category.setCid(CommonUtils.uuid());
            categoryService.add(category);
            findAll(request, response);
            request.setAttribute("msg", "添加成功");
        }
        return "f:adminjsps/msg.jsp";
    }

    // 删除表单回显
    public String deletePre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category category = categoryService.editPre(cid);
        request.setAttribute("category", category);
        return "f:adminjsps/admin/category/del.jsp";
    }

    //删除分类
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        try {
            categoryService.delete(cid);
            request.setAttribute("msg", "删除成功");
        } catch (CategoryException e) {
            request.setAttribute("msg", e.getMessage());
        }
        return "f:adminjsps/msg.jsp";
    }

    // 修改分类回显
    public String editPre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category category = categoryService.editPre(cid);
        request.setAttribute("category", category);
        return "f:adminjsps/admin/category/mod.jsp";
    }

    // 修改分类
    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
        categoryService.edit(category);
        findAll(request, response);
        request.setAttribute("msg", "修改成功");
        return "f:adminjsps/msg.jsp";
    }
}