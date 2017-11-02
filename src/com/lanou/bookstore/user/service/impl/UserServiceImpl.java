package com.lanou.bookstore.user.service.impl;

import com.lanou.bookstore.user.dao.UserDao;
import com.lanou.bookstore.user.dao.impl.UserDaoImpl;
import com.lanou.bookstore.user.domain.User;
import com.lanou.bookstore.user.domain.UserInfo;
import com.lanou.bookstore.user.domain.Wallet;
import com.lanou.bookstore.user.service.UserException;
import com.lanou.bookstore.user.service.UserService;

/**
 * Created by dllo on 17/9/21.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    // 注册
    public void refist(User form) throws UserException {
        User username = userDao.findByUsername(form.getUsername());
        if (username != null) {
            throw new UserException("用户名已被注册");
        }
        User email = userDao.findByEmail(form.getEmail());
        if (email != null) {
            throw new UserException("邮箱已经被注册");
        }
        userDao.regist(form);


    }

    @Override
    // 激活
    public boolean activation(String code) {
        return userDao.activation(code);
    }

    @Override
    // 登录
    public User login(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());
        if (user == null) {
        throw new UserException("用户名不存在");
    }
        if (!form.getPassword().equals(user.getPassword())) {
        throw new UserException("密码错误");
    }
        if (user.getState().equals("0")) {
        throw new UserException("用户尚未激活");
    }
        return user;
}

    @Override
    public void addInfo(UserInfo userInfo) {
        UserInfo info = userDao.loadInfo(userInfo.getUid());
        if (info==null){
            userDao.addInfo(userInfo);
        }else {
            userDao.updateInfo(userInfo);
        }


    }

    @Override
    public UserInfo loadInfo(String uid) {
       return userDao.loadInfo(uid);
    }

    @Override
    public void updatePWD(String newpass,String uid) {
        userDao.updatePWD(newpass,uid);
    }

    @Override
    public void recharge(String uid, String money) {
        userDao.recharge(uid,money);
    }

    @Override
    public void addWallet(User loginUser) {
        userDao.addWallet(loginUser);
    }

    @Override
    public Wallet loadWallet(String uid) {
       return userDao.loadWallet(uid);
    }

    @Override
    public void updateWallet(String balance2, String uid) {
        userDao.updateWallet(balance2,uid);
    }

    @Override
    public User findByName(String username) {
        return userDao.findByUsername(username);
    }


}
