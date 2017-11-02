package com.lanou.bookstore.category.admin.web.controller;

import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.order.service.impl.OrderServiceImpl;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dllo on 17/9/23.
 */
@WebServlet("/AdminOrderServlet")
public class AdminOrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    // 查询所有订单
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Order> orders = orderService.findAll();
        request.setAttribute("orders", orders);
        return "f:adminjsps/admin/order/list.jsp";
    }

    // 发货
    public String send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        orderService.send(oid);
        request.setAttribute("msg", "发货成功");
        findAll(request, response);
        return "f:adminjsps/msg.jsp";
    }

    // 分类查询订单
    public String findByState(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String state = request.getParameter("state");
        List<Order> orders = orderService.findByState(state);
        request.setAttribute("orders", orders);
        return "f:adminjsps/admin/order/list.jsp";
    }
}