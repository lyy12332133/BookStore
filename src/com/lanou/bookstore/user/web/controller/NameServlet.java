package com.lanou.bookstore.user.web.controller;

import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dllo on 17/9/28.
 */
@WebServlet("/NameServlet")
public class NameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        // ajax判断用户名是否注册
        UserService userService = new UserServiceImpl();
        User user = userService.findByName(username);
        if (user == null){
            response.getWriter().print(false);
        }else {
            response.getWriter().print(true);
        }
    }
}