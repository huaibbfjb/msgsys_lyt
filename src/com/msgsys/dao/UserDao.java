package com.msgsys.dao;

import com.msgsys.entity.User;

import java.util.List;

/**
 * 作者：LiuYunTao
 * 日期: 15:42 2020/11/26
 * 描述：
 */
public interface UserDao {
    int insert(User user);

    int update(User user);

    int delete(Integer id);

    List<User> querAll();

    User queryUserById(Integer id);

    User queryUserByNameAndPassword(User user);
}
