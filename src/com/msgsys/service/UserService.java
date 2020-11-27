package com.msgsys.service;

import com.msgsys.entity.User;

import java.util.List;

/**
 * 作者：LiuYunTao
 * 日期: 16:00 2020/11/26
 * 描述：
 */
public interface UserService {
    //注册
    int register(User user);

    //登录
    User login(User user);

    //查询所有
    List<User> queryAll();

    int update(User user);
}
