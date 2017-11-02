package com.lanou.bookstore.cart.cartdao.impl;

import com.lanou.bookstore.cart.cartdao.CartDao;
import com.lanou.bookstore.cart.domain.CartBean;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/28.
 */
public class CartDaoImpl implements CartDao {
    QueryRunner qr = new GxQueryRunner();

    @Override
    // 添加购物车条目
    public void add(CartBean cartBean) {
        String sql = "insert into cart values (?,?,?)";
        Object[] params = { cartBean.getUid(), cartBean.getBid(), cartBean.getCount()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 查询所有购物车条目
    public List<CartBean> findByUid(String uid) {
        String sql = "select * from cart where uid = ?";
        try {
            return qr.query(sql, new BeanListHandler<>(CartBean.class), uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 查询单本书条目
    public CartBean findByBid(String uid, String bid) {
        String sql = "select * from cart where uid = ? and bid = ?";
        try {
            return qr.query(sql, new BeanHandler<>(CartBean.class), uid, bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 修改图书数量
    public void updateCount(String uid,String bid, String count) {
        String sql = "update cart set count = ? where uid = ? and bid = ?";
        try {
            qr.update(sql, count, uid, bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 清空购物车
    public void clear(String uid) {
        String sql = "delete from cart where uid = ?";
        try {
            qr.update(sql, uid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 删除单个条目
    public void delete(String bid, String uid) {
        String sql = "delete from cart where uid = ? and bid = ?";
        try {
            qr.update(sql, uid, bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 修改图书数量
    public void update(String uid, String bid, String count) {
        String sql = "update cart set count = ? where uid = ? and bid = ?";
        try {
            qr.update(sql, count, uid, bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
