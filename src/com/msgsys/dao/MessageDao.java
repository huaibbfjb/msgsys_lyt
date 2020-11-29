package com.msgsys.dao;

import com.msgsys.entity.Message;

import java.util.List;

/**
 * 作者：LiuYunTao
 * 日期: 15:42 2020/11/26
 * 描述：
 */
public interface MessageDao {
    int insert(Message message);

    int update(Message message);

    int delete(Integer id);

    List<Message> querAll();

    //根据id查询邮件详情
    Message queryMessageById(Integer id);

    //根据收信人id查询邮件
    List<Message> queryMessageByToUid(Integer id);


}
