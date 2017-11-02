package com.lanou.bookstore.cart.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dllo on 17/9/21.
 */
public class Cart implements Serializable{
    private Map<String, CartItem> cartItems = new HashMap<>();

    public Cart() {
    }

    public Map<String, CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Map<String, CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void clear(){
        cartItems = new HashMap<>();
    }

    public void delete(String bid){
        cartItems.remove(bid);
        setCartItems(cartItems);
    }
}
