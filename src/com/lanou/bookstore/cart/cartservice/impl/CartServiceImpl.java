package com.lanou.bookstore.cart.cartservice.impl;

import com.lanou.bookstore.cart.cartdao.CartDao;
import com.lanou.bookstore.cart.cartdao.impl.CartDaoImpl;
import com.lanou.bookstore.cart.cartservice.CartService;
import com.lanou.bookstore.cart.domain.CartBean;

import java.util.List;

/**
 * Created by dllo on 17/9/28.
 */
public class CartServiceImpl implements CartService{
    private CartDao cartDao = new CartDaoImpl();

    @Override
    public void add(CartBean cartBean) {
        cartDao.add(cartBean);
    }

    @Override
    public List<CartBean> findByUid(String uid) {
       return cartDao.findByUid(uid);
    }

    @Override
    public CartBean findByBid(String uid, String bid) {
       return cartDao.findByBid(uid,bid);
    }

    @Override
    public void updateCount(String uid,String bid, String count) {
        cartDao.updateCount(uid,bid,count);
    }

    @Override
    public void clear(String uid) {
        cartDao.clear(uid);
    }

    @Override
    public void delete(String bid, String uid) {
        cartDao.delete(bid,uid);
    }

    @Override
    public void update(String uid, String bid, String count) {
        cartDao.update(uid,bid,count);
    }


}
