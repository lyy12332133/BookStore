package com.lanou.bookstore.order.service;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface OrderService {
    void add(Order order);

    void pay(Order order);

    List<Order> findByUid(String uid);

    void addOrderItemList(List<OrderItem> orderItemList);

    Book findByBid(String bid);

    Order load(String oid);

    void confirm(String oid) throws OrderException;

    List<Order> findAll();

    void send(String oid);

    List<Order> findByState(String state);

    void delete(String oid);

}
