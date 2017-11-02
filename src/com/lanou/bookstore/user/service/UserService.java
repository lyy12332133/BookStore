package com.lanou.bookstore.user.service;

import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.domain.UserInfo;
import com.lanou.bookstore.user.domain.Wallet;

/**
 * Created by dllo on 17/9/21.
 */
public interface UserService {
    void refist(User form) throws UserException;

    boolean activation(String code);

    User login(User user) throws UserException;

    void addInfo(UserInfo userInfo);

    UserInfo loadInfo(String uid);

    void updatePWD(String newpass,String uid);

    void recharge(String uid, String money);

    void addWallet(User loginUser);

    Wallet loadWallet(String uid);

    void updateWallet(String balance2, String uid);

    User findByName(String username);
}
