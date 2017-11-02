package com.lanou.bookstore.user.dao.impl;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.domain.UserInfo;
import com.lanou.bookstore.user.domain.Wallet;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Created by dllo on 17/9/21.
 */
public class UserDaoImpl implements UserDao {

    QueryRunner qr = new GxQueryRunner();

    @Override
    // 用户注册
    public void regist(User form) {
        String sql = "insert into tb_user values (?,?,?,?,?,?)";
        Object[] params = {form.getUid(), form.getUsername(), form.getPassword(),
                form.getEmail(), form.getCode(), form.getState()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 用户激活
    public boolean activation(String code) {
        String sql = "update tb_user set state = 1 where code = ?";
        try {
            int i = qr.update(sql, code);
            if (i == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 校验用户名是否存在
    public User findByUsername(String username) {
        String sql = "select * from tb_user where username = ?";
        try {
            return qr.query(sql, new BeanHandler<User>(User.class), username);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 校验邮箱是否存在
    public User findByEmail(String email) {
        String sql = "select * from tb_user where email = ?";
        try {
            return qr.query(sql, new BeanHandler<>(User.class), email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 首次添加信息
    public void addInfo(UserInfo u) {
        String sql = "insert into tb_info values (?,?,?,?,?,?)";
        Object[] params = {u.getUid(), u.getName(), u.getGender(), u.getBirthday(), u.getCellphone(), u.getAddress()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 修改信息表单回显
    public UserInfo loadInfo(String uid) {
        String sql = "select * from tb_info where uid = ?";
        try {
            return qr.query(sql, new BeanHandler<>(UserInfo.class), uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 修改个人信息
    public void updateInfo(UserInfo u) {
        String sql = "update tb_info set name=?,gender=?,birthday=?,cellphone=?,address=? where uid = ?";
        Object[] params = {u.getName(), u.getGender(), u.getBirthday(), u.getCellphone(), u.getAddress(), u.getUid()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 修改密码
    public void updatePWD(String newpass, String uid) {
        String sql = "update tb_user set password = ? where uid = ?";
        try {
            qr.update(sql, newpass, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 充值
    public void recharge(String uid, String money) {
        String sql = "select * from wallet where uid = ?";
        try {
            Wallet wallet = qr.query(sql, new BeanHandler<>(Wallet.class), uid);
            String balance = wallet.getBalance();
            Float num1 = Float.valueOf(money);
            Float num2 = Float.valueOf((balance));
            Float sum = num1 + num2;
            String balance2 = String.valueOf(sum);
            String sql1 = "update wallet set balance = ? where uid = ?";
            qr.update(sql1, balance2, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 首次登录创建钱包
    public void addWallet(User loginUser) {
        String sql = "insert into wallet values (?,?)";
        Object[] params = {loginUser.getUid(), "0"};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 查询钱包余额
    public Wallet loadWallet(String uid) {
        String sql = "select * from wallet where uid = ?";
        try {
            return qr.query(sql, new BeanHandler<>(Wallet.class), uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 更新钱包余额
    public void updateWallet(String balance2, String uid) {
        String sql = "update wallet set balance = ? where uid = ?";
        try {
            qr.update(sql, balance2, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
