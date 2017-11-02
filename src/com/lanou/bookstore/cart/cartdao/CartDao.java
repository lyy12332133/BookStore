package com.lanou.bookstore.cart.cartdao;

import com.lanou.bookstore.cart.domain.CartBean;

import java.util.List;

/**
 * Created by dllo on 17/9/28.
 */
public interface CartDao {
    void add(CartBean cartBean);

   List<CartBean> findByUid(String uid);

    CartBean findByBid(String uid, String bid);

    void updateCount(String uid,String bid, String count);

    void clear(String uid);

    void delete(String bid, String uid);

    void update(String uid, String bid, String count);


}
