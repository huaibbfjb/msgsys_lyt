package com.msgsys.service.impl;

import com.msgsys.dao.MessageDao;
import com.msgsys.dao.impl.MessageImpl;
import com.msgsys.entity.Message;
import com.msgsys.service.MessageSerive;

import java.util.List;

/**
 * 作者：LiuYunTao
 * 日期: 20:06 2020/11/26
 * 描述：
 */
public class MessageSeriveImpl implements MessageSerive {
    MessageDao messageDao=new MessageImpl();
    public List<Message> queryMessageByToUid(Integer id) {
        return messageDao.queryMessageByToUid(id);
    }

    public Message queryMessageById(Integer id) {
        return messageDao.queryMessageById(id);
    }
}
