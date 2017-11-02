package com.lanou.bookstore.cart.web.controller;

import com.lanou.bookstore.cart.cartservice.CartService;
import com.lanou.bookstore.cart.cartservice.impl.CartServiceImpl;

import com.lanou.bookstore.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by dllo on 17/9/28.
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
    private CartService cartService = new CartServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String bid = request.getParameter("bid");
        User user = (User) request.getSession().getAttribute("user");
        String count = request.getParameter("count");
        int num = Integer.parseInt(count) + 1;
        // 更改数据库中的该本书count的值
        cartService.update(user.getUid(), bid, String.valueOf(num));
    }

}