package com.lanou.bookstore.cart.web.controller;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.book.service.BookService;
import com.lanou.bookstore.book.service.impl.BookServiceImpl;
import com.lanou.bookstore.cart.cartservice.CartService;
import com.lanou.bookstore.cart.cartservice.impl.CartServiceImpl;
import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartBean;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.service.UserException;

import com.lanou.commons.CommonUtils;
import com.lanou.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by dllo on 17/9/28.
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
    private CartService cartService = new CartServiceImpl();
    private BookService bookService = new BookServiceImpl();

    // 添加购物车
    public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 得到编号
        // 得到编号
        String bid = request.getParameter("bid");
        String count = request.getParameter("count");
        CartBean cartBean = new CartBean();
        User user = (User) request.getSession().getAttribute("user");
        // 判断用户是否存在
        if (user == null) {
            // 用户不存在将图书保存在session中的购物车中
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            // 判断购物车是否存在
            if (cart == null) {
                // 购物车不存在, 创建购物车存图书
                cart = new Cart();
                Map<String, CartItem> cartItemMap = cart.getCartItems();
                CartItem cartItem = new CartItem();
                Book book = bookService.load(bid);
                cartItem.setBook(book);
                cartItem.setCount(count);
                cartItemMap.put(bid, cartItem);
            } else {
                // 购物车存 判断是否有相同的书
                Map<String, CartItem> map = cart.getCartItems();
                Book book = bookService.load(bid);
                CartItem cartItem = map.get(bid);
                // 没有保存
                if (cartItem == null) {
                    CartItem cartItem1 = new CartItem();
                    cartItem1.setBook(book);
                    cartItem1.setCount(count);
                    map.put(bid, cartItem1);
                    // 有修改数量
                } else {
                    int cnt1 = Integer.parseInt(cartItem.getCount());
                    int cnt2 = Integer.parseInt(count);
                    int num = cnt1 + cnt2;
                    String sum = String.valueOf(num);
                    cartItem.setCount(sum);
                    map.put(bid, cartItem);
                }
            }
            request.getSession().setAttribute("cart", cart);
            request.getRequestDispatcher("jsps/cart/noLoginlist.jsp").forward(request, response);
        } else {
            // 用户存在将图书保存到数据库中
            CartBean byBid = cartService.findByBid(user.getUid(), bid);
            // 判断数据库中是否有相同的书,有修改数量
            if (byBid != null) {
                int cnt1 = Integer.parseInt(byBid.getCount());
                int cnt2 = Integer.parseInt(count);
                int num = cnt1 + cnt2;
                String sum = String.valueOf(num);
                cartService.updateCount(byBid.getUid(),byBid.getBid(), sum);
            } else {
                // 没有添加到数据库
                cartBean.setCid(CommonUtils.uuid());
                cartBean.setUid(user.getUid());
                cartBean.setBid(bid);
                cartBean.setCount(count);
                cartService.add(cartBean);
            }
            // 将数据库中的购物车条目保存的session购物车中
            List<CartBean> cartBeanList = cartService.findByUid(user.getUid());
            Cart cart = new Cart();
            Map<String, CartItem> map = cart.getCartItems();
            for (CartBean bean : cartBeanList) {
                // 通过编号得到书
                Book book = bookService.load(bean.getBid());
                CartItem cartItem = new CartItem(book, bean.getCount());
                map.put(bean.getBid(), cartItem);
            }
            request.getSession().setAttribute("cart", cart);
        }
        return "f:/jsps/cart/list.jsp";
    }

    // 清空购物车
    public String clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        // 通过 uid 删除所有购物车条目
        cartService.clear(user.getUid());
        Cart cart = new Cart();
        request.getSession().setAttribute("cart", cart);
        return "f:/jsps/cart/list.jsp";
    }

    // 删除单本书
    public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {
        User user = (User) request.getSession().getAttribute("user");
        String bid = request.getParameter("bid");
        // uid 和 bid 删除购物车条目
        cartService.delete(bid, user.getUid());
        BookService bookService = new BookServiceImpl();
        // 再查询出所有的购物车条目 回显
        List<CartBean> cartBeanList = cartService.findByUid(user.getUid());
        Cart cart = new Cart();
        Map<String, CartItem> map = cart.getCartItems();
        for (CartBean bean : cartBeanList) {
            // 通过 bid 得到书
            Book book = bookService.load(bean.getBid());
            CartItem cartItem = new CartItem(book, bean.getCount());
            map.put(bean.getBid(), cartItem);
        }
        request.getSession().setAttribute("cart", cart);
        return "f:/jsps/cart/list.jsp";
    }

    // 我的购物车
    public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {
        User user = (User) request.getSession().getAttribute("user");
        // 通过 uid 获取购物车条目
        List<CartBean> cartBeanList = cartService.findByUid(user.getUid());
        Cart cart = new Cart();
        Map<String, CartItem> map = cart.getCartItems();
        for (CartBean bean : cartBeanList) {
            // 通过编号得到书
            Book book = bookService.load(bean.getBid());
            CartItem cartItem = new CartItem(book, bean.getCount());
            // 把购物车条目存到购物车中
            map.put(bean.getBid(), cartItem);
        }
        request.getSession().setAttribute("cart", cart);
        return "f:/jsps/cart/list.jsp";

    }

    // 查询未登录购物车的所有图书
    public String findAllNoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        request.getSession().setAttribute("cart", cart);
        return "f:/jsps/cart/noLoginlist.jsp";

    }

    // 删除未登录的购物车中的全部图书
    public String clearNoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = new Cart();
        request.getSession().setAttribute("cart", cart);
        return "f:/jsps/cart/list.jsp";
    }

    // 删除未登录的购物车中的指定图书
    public String deleteNoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UserException {
        String bid = request.getParameter("bid");
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        Map<String, CartItem> map = cart.getCartItems();
        map.remove(bid);
        cart.setCartItems(map);
        request.getSession().setAttribute("cart", cart);
        return "f:/jsps/cart/noLoginlist.jsp";
    }

}