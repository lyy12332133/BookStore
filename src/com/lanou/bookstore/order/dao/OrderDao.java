package com.lanou.bookstore.order.dao;

import com.lanou.bookstore.book.domain.Book;
import com.lanou.bookstore.order.domain.Order;
import com.lanou.bookstore.order.domain.OrderItem;

import java.util.List;

/**
 * Created by dllo on 17/9/21.
 */
public interface OrderDao {
    void addOrder(Order order);

    void addOrderItemList(List<OrderItem> orderItemList);

    void pay(Order order);

    List<Order> findByUid(String uid);

    Book findByBid(String bid);

    Order load(String oid);

    Order getStateById(String oid);

    void updateState(String oid, int i);

    List<Order> findAll();

    void send(String oid);

    List<Order> findByState(String state);

    void delete(String oid);


}
