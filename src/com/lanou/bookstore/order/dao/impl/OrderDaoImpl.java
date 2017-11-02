package com.lanou.bookstore.order.dao.impl;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class OrderDaoImpl implements OrderDao {
    private QueryRunner qr = new GxQueryRunner();

    @Override
    // 用户添加订单
    public void addOrder(Order order) {
        String sql = "insert into orders values (?,?,?,?,?,?,?)";
        Object[] params = {order.getOid(), order.getOrdertime(), order.getTotal(),
                order.getState(), order.getUid(), order.getAddress(), false};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 订单信息
    public void addOrderItemList(List<OrderItem> orderItemList) {
        String sql = "insert into orderitem values (?,?,?,?,?)";
        for (OrderItem item : orderItemList) {
            Object[] params = {item.getIid(), item.getCount(), item.getSubtotal(),
                    item.getOid(), item.getBid()};
            try {
                qr.update(sql, params);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    // 用户支付订单
    public void pay(Order order) {
        String sql = "update orders set address = ?,state = ? where oid = ?";
        Object[] params = {order.getAddress(), order.getState(), order.getOid()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 用户查询所有订单
    public List<Order> findByUid(String uid) {
        String sql = "select * from orders where del = false and uid = ? order by ordertime desc";
        try {
            List<Order> orders = qr.query(sql, new BeanListHandler<>(Order.class), uid);
            for (Order order : orders) {
                String oid = order.getOid();
                String sql1 = "select * from orderitem where oid = ?";
                List<OrderItem> orderItems = qr.query(sql1, new BeanListHandler<>(OrderItem.class), oid);
                for (OrderItem orderItem : orderItems) {
                    String bid = orderItem.getBid();
                    String sql2 = "select * from book where bid = ?";
                    Book book = qr.query(sql2, new BeanHandler<>(Book.class), bid);
                    orderItem.setBook(book);
                }
                order.setOrderItemList(orderItems);
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 查询单本书
    public Book findByBid(String bid) {
        String sql = "select * from book where bid = ?";
        try {
            return qr.query(sql, new BeanHandler<>(Book.class), bid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 查询本次订单
    public Order load(String oid) {
        String sql = "select * from orders where oid = ?";
        try {
            Order order = qr.query(sql, new BeanHandler<>(Order.class), oid);
            String sql1 = "select * from orderitem where oid = ?";
            List<OrderItem> orderItems = qr.query(sql1, new BeanListHandler<>(OrderItem.class), oid);
            for (OrderItem orderItem : orderItems) {
                String bid = orderItem.getBid();
                String sql2 = "select * from book where bid = ?";
                Book book = qr.query(sql2, new BeanHandler<>(Book.class), bid);
                orderItem.setBook(book);
            }
            order.setOrderItemList(orderItems);
            return order;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 校验收货
    public Order getStateById(String oid) {
        String sql = "select state from orders where oid = ?";
        try {
            return qr.query(sql, new BeanHandler<>(Order.class), oid);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 确认收货
    public void updateState(String oid, int i) {
        String sql = "update orders set state = ? where oid = ?";
        try {
            qr.update(sql, i, oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 管理员查询所有订单
    public List<Order> findAll() {
        String sql = "select * from orders order by ordertime desc";
        try {
            List<Order> orders = qr.query(sql, new BeanListHandler<>(Order.class));
            for (Order order : orders) {
                String oid = order.getOid();
                String sql1 = "select * from orderitem where oid = ?";
                List<OrderItem> orderItems = qr.query(sql1, new BeanListHandler<>(OrderItem.class), oid);
                for (OrderItem orderItem : orderItems) {
                    String bid = orderItem.getBid();
                    String sql2 = "select * from book where bid = ?";
                    Book book = qr.query(sql2, new BeanHandler<>(Book.class), bid);
                    orderItem.setBook(book);
                }
                order.setOrderItemList(orderItems);
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 管理员发货
    public void send(String oid) {
        String sql = "update orders set state = 3 where oid = ?";
        try {
            qr.update(sql, oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    // 管理员分类查询
    public List<Order> findByState(String state) {
        String sql = "select * from orders where state = ? order by ordertime desc";
        try {
            List<Order> orders = qr.query(sql, new BeanListHandler<>(Order.class), state);
            for (Order order : orders) {
                String oid = order.getOid();
                String sql1 = "select * from orderitem where oid = ?";
                List<OrderItem> orderItems = qr.query(sql1, new BeanListHandler<>(OrderItem.class), oid);
                for (OrderItem orderItem : orderItems) {
                    String bid = orderItem.getBid();
                    String sql2 = "select * from book where bid = ?";
                    Book book = qr.query(sql2, new BeanHandler<>(Book.class), bid);
                    orderItem.setBook(book);
                }
                order.setOrderItemList(orderItems);
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    // 用户删除订单
    public void delete(String oid) {
        String sql = "update orders set del = true where oid = ?";
        try {
            qr.update(sql, oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
