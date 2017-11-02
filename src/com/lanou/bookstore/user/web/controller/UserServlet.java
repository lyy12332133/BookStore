package com.lanou.bookstore.user.web.controller;

import com.lanou.bookstore.cart.cartservice.CartService;
import com.lanou.bookstore.cart.cartservice.impl.CartServiceImpl;
import com.lanou.bookstore.cart.domain.Cart;
import com.lanou.bookstore.cart.domain.CartBean;
import com.lanou.bookstore.cart.domain.CartItem;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.domain.UserInfo;
import com.lanou.bookstore.user.domain.Wallet;
import com.lanou.bookstore.user.service.UserException;
import com.lanou.bookstore.user.service.UserService;
import com.lanou.bookstore.user.service.impl.UserServiceImpl;
import com.lanou.commons.CommonUtils;
import com.lanou.mail.Mail;
import com.lanou.mail.MailUtils;
import com.lanou.servlet.BaseServlet;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dllo on 17/9/21.
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();
    private CartService cartService = new CartServiceImpl();

    // 注册
    public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        User form = CommonUtils.toBean(request.getParameterMap(), User.class);
        request.setAttribute("form", form);
        boolean a = formValidation(request, response, form);
        if (a) {
            form.setUid(CommonUtils.uuid());
            form.setCode(CommonUtils.uuid() + CommonUtils.uuid());
            form.setState("0");
            try {
                // 注册
                userService.refist(form);
            } catch (UserException e) {
                request.setAttribute("msg", e.getMessage());
                request.getRequestDispatcher("jsps/user/regist.jsp").forward(request, response);
            }
            try {
                // 发邮件
                sendEmail(form);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                request.setAttribute("msg", "注册成功,请到邮箱中激活!");
            }
        } else {
            request.getRequestDispatcher("jsps/user/regist.jsp").forward(request, response);
        }
        return "f:jsps/msg.jsp";
    }

    // 登录
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        User user = CommonUtils.toBean(request.getParameterMap(), User.class);
        request.setAttribute("form", user);
        // 表单校验
        boolean b = isformTrue(request, user);
        if (b) {
            User loginUser = null;
            try {
                loginUser = userService.login(user);
                // 判断钱包是否存在, 不存在创建钱包
                Wallet wallet = userService.loadWallet(loginUser.getUid());
                if (wallet == null) {
                    userService.addWallet(loginUser);
                }
                // 把用户信息存到session中
                request.getSession().setAttribute("user", loginUser);
            } catch (UserException e) {
                request.setAttribute("msg", e.getMessage());
                request.getRequestDispatcher("jsps/user/login.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("jsps/user/login.jsp").forward(request, response);
        }

        try {

            User login = userService.login(user);
            Cart cart = (Cart) request.getSession().getAttribute("cart");
            // 如果session购物车中有图书, 添加到用户购物车中
            if (cart != null) {
                Map<String, CartItem> cartItems = cart.getCartItems();
                for (String key : cartItems.keySet()) {
                    CartItem cartItem = cartItems.get(key);
                    String count = cartItem.getCount();
                    String bid = cartItem.getBook().getBid();
                    String uid = login.getUid();
                    CartBean byBid = cartService.findByBid(login.getUid(), bid);
                    // 判断数据库中是否有相同的书,有修改数量
                    if (byBid != null) {
                        System.out.println(666);
                        int cnt1 = Integer.parseInt(byBid.getCount());
                        int cnt2 = Integer.parseInt(count);
                        int num = cnt1 + cnt2;
                        String sum = String.valueOf(num);
                        cartService.updateCount(byBid.getUid(),byBid.getBid(),sum);
                    } else {
                        System.out.println(111);
                        CartBean cartBean = new CartBean();
                        cartBean.setUid(uid);
                        cartBean.setBid(bid);
                        cartBean.setCount(count);
                        cartService.add(cartBean);
                    }

                }

            }
            UserInfo userInfo = userService.loadInfo(login.getUid());
            // 保存用户信息
            request.getSession().setAttribute("userInfo", userInfo);
            Wallet wallet = userService.loadWallet(login.getUid());
            // 保存账户余额
            request.getSession().setAttribute("balance", wallet.getBalance());
        } catch (UserException e) {
            e.printStackTrace();
        }
        request.getSession().removeAttribute("cart");
        return "f:index.jsp";
    }
    // 退出
    public void quit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        request.getSession().removeAttribute("user");
        request.getSession().removeAttribute("cart");
        response.sendRedirect("index.jsp");
    }

    // 登录表单校验
    private boolean isformTrue(HttpServletRequest request, User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        int m = username.trim().length();
        int n = password.trim().length();
        Map<String, String> maps = new HashMap<>();
        boolean a = true;
        if (m == 0) {
            maps.put("nameError", "用户名不能为空");
            a = false;
        }
        if (n == 0) {
            maps.put("pwdError", "密码不能为空");
            a = false;
        }
        request.setAttribute("maps", maps);
        return a;
    }

    // 注册表单校验
    private boolean formValidation(HttpServletRequest request, HttpServletResponse response, User form) throws ServletException, IOException {
        boolean b = checkEmaile(form.getEmail());
        String username = form.getUsername();
        String password = form.getPassword();
        int m = username.trim().length();
        int n = password.trim().length();
        Map<String, String> maps = new HashMap<>();
        boolean a = true;
        if (m < 3 || m > 15) {
            maps.put("nameError", "用户名长度必须在3~15之间");
            a = false;
        }
        if (n < 3 || n > 15) {
            maps.put("pwdError", "密码长度必须在3~15之间");
            a = false;
        }
        if (m == 0) {
            maps.put("nameError", "用户名不能为空");
            a = false;
        }
        if (n == 0) {
            maps.put("pwdError", "密码不能为空");
            a = false;
        }
        if (!b) {
            maps.put("emailError", "请输入正确的邮箱");
            a = false;
        }
        request.setAttribute("maps", maps);
        return a;
    }

    // 发送邮件
    private void sendEmail(User form) throws MessagingException, IOException {
        // 使用模板发送邮件
        Session session = MailUtils.createSession("smtp.163.com", "15898126129@163.com", "yz951018");
        // 传递激活码
        String url = "<a href='http://localhost:8080/UserServlet?method=activation&code="
                + form.getCode() + "'>点击激活</a> 或者复制链接访问: " +
                "http://localhost:8080/UserServlet?method=activation&code=" + form.getCode() + "";
        Mail mail = new Mail("15898126129@163.com", form.getEmail(), "请您在邮箱中激活", url);
        MailUtils.send(session, mail);
        try {
            MailUtils.send(session, mail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // 激活
    public String activation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        String code = request.getParameter("code");
        // 激活
        boolean b = userService.activation(code);
        if (b) {
            request.setAttribute("msg", "激活成功");
        } else {
            request.setAttribute("msg", "激活失败");
        }
        return "f:jsps/msg.jsp";
    }

    // 邮箱正则表达式
    private static boolean checkEmaile(String emaile) {
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        Pattern p = Pattern.compile(RULE_EMAIL);
        Matcher m = p.matcher(emaile);
        return m.matches();
    }

    //修改信息表单回显
    public String loadInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        User user = (User) request.getSession().getAttribute("user");
        String uid = user.getUid();
        UserInfo userInfo = userService.loadInfo(uid);
        request.setAttribute("userInfo", userInfo);
        return "f:jsps/info/info.jsp";
    }

    // 修改信息
    public String addInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {

        UserInfo userInfo = CommonUtils.toBean(request.getParameterMap(), UserInfo.class);
        User user = (User) request.getSession().getAttribute("user");
        userInfo.setUid(user.getUid());
        userService.addInfo(userInfo);
        request.setAttribute("msg", "修改信息成功");
        return "f:jsps/info/msg.jsp";
    }

    // 修改密码
    public void updatePWD(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException {
        String oldpass = request.getParameter("oldpass");
        String newpass = request.getParameter("newpass");
        if (oldpass.length() == 0 || newpass.length() == 0) {
            request.setAttribute("msg", "密码不能为空");
            request.getRequestDispatcher("jsps/user/updateps.jsp").forward(request, response);
        }
        User user = (User) request.getSession().getAttribute("user");
        if (oldpass.equals(user.getPassword())) {
            userService.updatePWD(newpass, user.getUid());
            request.setAttribute("msg", "密码修改成功");
            request.getRequestDispatcher("jsps/user/msg.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "密码错误");
            request.getRequestDispatcher("jsps/user/updateps.jsp").forward(request, response);
        }
    }

    // 充值
    public String recharge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, UserException {
        User user = (User) request.getSession().getAttribute("user");
        String money = request.getParameter("money");
        if (money.length() == 0) {
            request.setAttribute("msg", "输入的金额不能为空");
            return "f:jsps/usercenter/usermoney.jsp";
        }
        User login = userService.login(user);
        String uid = login.getUid();
        userService.recharge(uid, money);
        request.setAttribute("msg", "充值成功");
        return "f:jsps/info/msg.jsp";
    }

    // 查询钱包是否存在
    public String loadWallet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, UserException {
        User user = (User) request.getSession().getAttribute("user");
        Wallet wallet = userService.loadWallet(user.getUid());
        request.getSession().setAttribute("money", wallet.getBalance());
        return "f:jsps/usercenter/usermoney.jsp";
    }
}