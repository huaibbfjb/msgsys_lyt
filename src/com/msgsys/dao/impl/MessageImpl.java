package com.msgsys.dao.impl;

import com.msgsys.dao.BaseDao;
import com.msgsys.dao.MessageDao;
import com.msgsys.entity.Message;

import java.util.List;


/**
 * 作者：LiuYunTao
 * 日期: 19:40 2020/11/26
 * 描述：
 */
public class MessageImpl extends BaseDao implements MessageDao {

    public int insert(Message message) {
        String sql = "INSERT INTO T_msg(fromUid,toUid,mtitle,mcontent,readflag,createTime)VALUES(?,?,?,?,?,?)";
        return update(sql, message.getFromUid(), message.getToUid(), message.getmTitle(),
                message.getmContent(), message.getReadFlag(), message.getCreateTime());
    }

    public int update(Message message) {
        String sql = "update t_msg set fromUid=?,toUid=?,mtitle=?,mcontent=?,readflag=?,createTime=? where id=?";
        return update(sql, message.getFromUid(), message.getToUid(), message.getmTitle(),
                message.getmContent(), message.getReadFlag(), message.getCreateTime(), message.getId());
    }

    public int delete(Integer id) {
        String sql = "delete from t_msg where id=?";
        return update(sql, id);
    }

    public List<Message> querAll() {
        String sql = "select * from t_msg";
        return queryForList(Message.class, sql);
    }

    public Message queryMessageById(Integer id) {
        String sql = "select * from t_msg where id=?";
        return queryForOne(Message.class, sql, id);
    }

    public List<Message> queryMessageByToUid(Integer id) {
        String sql = "select * from t_msg where toUid=?";
        return queryForList(Message.class, sql, id);
    }


}
