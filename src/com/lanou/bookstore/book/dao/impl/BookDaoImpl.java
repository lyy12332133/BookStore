package com.lanou.bookstore.book.dao.impl;

import com.lanou.bookstore.book.dao.BookDao;
import com.lanou.bookstore.book.domain.Book;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class BookDaoImpl implements BookDao {
    private QueryRunner qr = new GxQueryRunner();

    @Override
    // 查询所有书籍
    public List<Book> findAll() {
        String sql = "select * from book where del = false";
        try {
            return qr.query(sql, new BeanListHandler<>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 查询单本书籍
    public Book load(String bid) {
        String sql = "select * from book where del = false and bid = ?";
        try {
            return qr.query(sql, new BeanHandler<>(Book.class), bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 分类查询书籍
    public List<Book> findByCategory(String cid) {
        String sql = "select * from book where del = false and cid = ?";
        try {
            return qr.query(sql, new BeanListHandler<>(Book.class), cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 编辑书籍
    public void edit(Book book) {
        String sql = "update book set bname=?,price=?,author=?,image=?,cid=? where bid = ?";

        Object[] params = {book.getBname(), book.getPrice(), book.getAuthor(),
                book.getImage(), book.getCid(), book.getBid()};

        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 删除书籍
    public void del(String bid) {
        String sql = "update book set del = true where bid = ?";
        try {
            qr.update(sql, bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 添加新书
    public void add(Book book) {
        String sql = "insert into book values (?,?,?,?,?,?,?,?,?)";
        Object[] params = {book.getBid(), book.getBname(), book.getPrice(), book.getAuthor(),
                book.getImage(), book.getCid(), book.getDel(), book.getSales(),book.getContent()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 查询回收站图书
    public List<Book> recover() {
        String sql = "select * from book where del = true";
        try {
            return qr.query(sql, new BeanListHandler<>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 回收图书
    public void recoverLoad(String bid) {
        String sql = "update book set del = false where bid = ?";
        try {
            qr.update(sql, bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 更新销量
    public void updateSales(int sum, String bid) {
        String sql = "update book set sales = ? where bid = ?";
        try {
            qr.update(sql, sum, bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 价格降序查询
    public List<Book> findAllByPriceDown() {
        String sql = "select * from book where del = false order by price desc";
        try {
            return qr.query(sql, new BeanListHandler<>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    // 销量查询
    public List<Book> findAllBySales() {
        String sql = "select * from book where del = false order by sales desc";
        try {
            return qr.query(sql, new BeanListHandler<>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 价格升序查询
    public List<Book> findAllByPriceUp() {
        String sql = "select * from book where del = false order by price asc";
        try {
            return qr.query(sql, new BeanListHandler<>(Book.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 分类销量查询
    public List<Book> findBySales(String cid) {
        String sql = "select * from book where del = false and cid = ? order by sales desc";
        try {
            return qr.query(sql, new BeanListHandler<>(Book.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 分类由高到低查询
    public List<Book> findByPriceDown(String cid) {
        String sql = "select * from book where del = false and cid = ? order by price desc";
        try {
            return qr.query(sql, new BeanListHandler<>(Book.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 分类由低到高查询
    public List<Book> findByPriceUp(String cid) {
        String sql = "select * from book where del = false and cid = ? order by price asc";
        try {
            return qr.query(sql, new BeanListHandler<>(Book.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
