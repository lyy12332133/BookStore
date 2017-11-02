package com.lanou.bookstore.order.web.controller;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.cart.cartservice.CartService;
import com.lanou.bookstore.cart.cartservice.impl.CartServiceImpl;
import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartBean;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderException;
import com.lanou.bookstore.order.service.OrderService;
import com.lanou.bookstore.order.service.impl.OrderServiceImpl;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.domain.Wallet;
import com.lanou.bookstore.user.service.UserException;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();
    private CartService cartService = new CartServiceImpl();
    private BookService bookService = new BookServiceImpl();

    // 添加订单到数据库
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {
        User user = (User) request.getSession().getAttribute("user");
        List<CartBean> cartBeanList = cartService.findByUid(user.getUid());
        Cart cart = new Cart();
        // 判断购物车是否为空
        if (cartBeanList.size() == 0) {
            request.setAttribute("msg", "购物车为空, 请去添加商品呢, 亲");
            request.getRequestDispatcher("jsps/order/msg.jsp").forward(request, response);
        } else {
            // 新建订单
            Order order = new Order();
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = format.format(date);
            String sum = request.getParameter("sum");
            // 判断订单是否为空
            if (sum.length() == 0 || Double.valueOf(sum) == 0.00) {
                request.setAttribute("msg", "订单为空, 请核对后再下单欧, 亲");
                request.getRequestDispatcher("jsps/order/msg.jsp").forward(request, response);
            } else {

                // 创建订单表
                Double num = Double.valueOf(request.getParameter("sum"));
                order.setOid(CommonUtils.uuid());
                order.setOrdertime(time);
                order.setState(1);
                order.setUid(user.getUid());
                order.setTotal(num);
                order.setAddress(null);
                // 将订单添加到数据库
                orderService.add(order);
                // 创建 orderItem集合 遍历存图书 和 数量
                List<OrderItem> orderItemList = new ArrayList<>();
                for (CartBean cartBean : cartBeanList) {
                    String bid = cartBean.getBid();
                    Object o = request.getSession().getAttribute(bid);
                    if (o != null) {
                        Book book = bookService.load(bid);
                        OrderItem orderItem = new OrderItem();
                        int count = Integer.valueOf(cartBean.getCount());
                        Double price = Double.valueOf(book.getPrice());
                        orderItem.setIid(CommonUtils.uuid());
                        orderItem.setCount(count);
                        orderItem.setSubtotal(count * price);
                        orderItem.setOid(order.getOid());
                        orderItem.setBook(book);
                        orderItem.setBid(bid);
                        orderItemList.add(orderItem);
                        // 删除购物车中已结算的图书
                        cartService.delete(bid, user.getUid());
                    }
                }
                order.setOrderItemList(orderItemList);
                orderService.addOrderItemList(orderItemList);
                request.setAttribute("order", order);
            }
        }
        return "f:/jsps/order/desc.jsp";
    }

    // 显示本次订单
    public String load(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        Order order = orderService.load(oid);
        request.setAttribute("order", order);
        return "f:/jsps/order/desc.jsp";
    }

    // 支付订单
    public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        String uid = request.getParameter("uid");
        Order order = new Order();
        // 查询余额, 判断是否不足
        UserService userService = new UserServiceImpl();
        Wallet wallet = userService.loadWallet(uid);
        String balance = wallet.getBalance();
        String total = request.getParameter("total");
        Float count = Float.valueOf(balance);
        Float money = Float.valueOf(total);
        Float num = count - money;
        if (num < 0) {
            request.setAttribute("msg", "余额不足请前去充值");
            request.getRequestDispatcher("jsps/order/msg.jsp").forward(request, response);
        } else {

            String balance2 = String.valueOf(num);
            userService.updateWallet(balance2, uid);
            order.setAddress(request.getParameter("address"));
            order.setState(2);
            order.setOid(oid);
            // 支付订单
            orderService.pay(order);
            request.setAttribute("msg", "恭喜您, 支付成功!");
        }
        return "f:/jsps/order/msg.jsp";
    }

    // 确认收货
    public String confirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        // 查询订单
        Order order = orderService.load(oid);
        List<OrderItem> orderItemList = order.getOrderItemList();
        // 更新每本书的销量
        for (OrderItem orderItem : orderItemList) {
            Integer sales = orderItem.getBook().getSales();
            Integer count = orderItem.getCount();
            int sum = count + sales;
            BookService bookService = new BookServiceImpl();
            bookService.updateSales(sum, orderItem.getBook().getBid());
        }
        try {
            orderService.confirm(oid);
        } catch (OrderException e) {
            request.setAttribute("msg", e.getMessage());
        }


        BookService bookService = new BookServiceImpl();
        List<Book> books = bookService.findAll();
        int sales = 0;
        for (Book book : books) {
            Integer sale = book.getSales();
            sales += sale;
        }
        // 回显总销量
        request.getSession().setAttribute("sales", sales);
        request.setAttribute("msg", "确认成功, 交易完成");
        return "f:/jsps/order/msg.jsp";
    }

    // 我的订单
    public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        // 通过uid查询订单
        List<Order> orders = orderService.findByUid(user.getUid());
        request.setAttribute("orders", orders);
        return "f:/jsps/order/list.jsp";
    }

    // 用户删除订单
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        // 通过oid删除订单
        orderService.delete(oid);
        request.setAttribute("msg", "订单删除成功");
        return "f:/jsps/order/msg.jsp";
    }
}