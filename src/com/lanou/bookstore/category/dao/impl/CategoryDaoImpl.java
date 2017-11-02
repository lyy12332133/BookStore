package com.lanou.bookstore.category.dao.impl;

import com.lanou.bookstore.category.dao.CategoryDao;
import com.lanou.bookstore.category.domain.Admin;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryException;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class CategoryDaoImpl implements CategoryDao {
    private QueryRunner qr = new GxQueryRunner();

    @Override
    // 查询所有分类
    public List<Category> findAll() {
        String sql = "select * from category";
        try {
            return qr.query(sql, new BeanListHandler<>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 管理员登录
    public boolean login(String adminname, String password) {
        String sql = "select * from tb_admin where adminname = ? and password = ?";
        try {
            Admin admin = qr.query(sql, new BeanHandler<>(Admin.class), adminname, password);
            if (admin == null) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 添加分类
    public void add(Category category) {
        String sql = "insert into category values (?,?)";
        try {
            qr.update(sql, category.getCid(), category.getCname());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 删除分类
    public void delete(String cid) throws CategoryException {

        String sql = "select count(*) from book where cid = ?";
        try {
            Number num = qr.query(sql, new ScalarHandler<>(), cid);
            int value = num.intValue();
            if (value > 0) {
                throw new CategoryException("此类书籍不为空, 不能删除");
            } else {
                String sql1 = "delete from category where cid = ?";
                qr.update(sql1, cid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    //表单回显
    public Category editPre(String cid) {
        String sql = "select * from category where cid = ?";
        try {
            return qr.query(sql, new BeanHandler<>(Category.class), cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 修改分类
    public void edit(Category category) {
        String sql = "update category set cname = ? where cid = ?";
        try {
            qr.update(sql, category.getCname(), category.getCid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 查询总销量
    public Admin findSalesByName(String adminname) {
        String sql = "select * from tb_admin where adminname = ?";
        try {
            return qr.query(sql, new BeanHandler<>(Admin.class), adminname);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
