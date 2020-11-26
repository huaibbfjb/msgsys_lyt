package com.msgsys.service;

import com.msgsys.entity.Message;

import java.util.List;

/**
 * 作者：LiuYunTao
 * 日期: 19:58 2020/11/26
 * 描述：
 */
public interface MessageSerive {
    //根据收信人id查询邮件
    List<Message> queryMessageByToUid(Integer id);

    //根据id查询邮件详情
    Message queryMessageById(Integer id);
}
