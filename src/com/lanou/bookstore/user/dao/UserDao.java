package com.lanou.bookstore.user.dao;

import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.domain.UserInfo;
import com.lanou.bookstore.user.domain.Wallet;

/**
 * Created by dllo on 17/9/21.
 */
public interface UserDao {
    void regist(User form);

    boolean activation(String code);

    User findByUsername(String username);

    User findByEmail(String email);

    void addInfo(UserInfo userInfo);

    UserInfo loadInfo(String uid);

    void updateInfo(UserInfo userInfo);

    void updatePWD(String newpass, String uid);

    void recharge(String uid, String money);

    void addWallet(User loginUser);

    Wallet loadWallet(String uid);

    void updateWallet(String balance2, String uid);

}
