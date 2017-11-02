package com.lanou.bookstore.order.service.impl;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.order.dao.OrderDao;
import com.lanou.bookstore.order.dao.impl.OrderDaoImpl;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;
import com.lanou.bookstore.order.service.OrderException;
import com.lanou.bookstore.order.service.OrderService;


import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public class OrderServiceImpl implements OrderService{
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public void add(Order order) {
        orderDao.addOrder(order);
    }

    @Override
    public void pay(Order order) {
        orderDao.pay(order);
    }

    @Override
    public List<Order> findByUid(String uid) {
       return orderDao.findByUid(uid);
    }

    @Override
    public void addOrderItemList(List<OrderItem> orderItemList) {
        orderDao.addOrderItemList(orderItemList);
    }

    @Override
    public Book findByBid(String bid) {
       return orderDao.findByBid(bid);
    }

    @Override
    public Order load(String oid) {
       return orderDao.load(oid);
    }

    @Override
    public void confirm(String oid) throws OrderException{
        Order order = orderDao.getStateById(oid);
        if (order.getState()!=3){
            throw new OrderException("订单确认失败, 你不是好人");
        }
        orderDao.updateState(oid,4);

    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public void send(String oid) {
        orderDao.send(oid);
    }

    @Override
    public List<Order> findByState(String state) {
       return orderDao.findByState(state);
    }

    @Override
    public void delete(String oid) {
        orderDao.delete(oid);
    }

}
