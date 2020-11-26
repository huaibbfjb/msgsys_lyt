package com.msgsys.servlet;

import com.msgsys.entity.Message;
import com.msgsys.entity.User;
import com.msgsys.service.MessageSerive;
import com.msgsys.service.impl.MessageSeriveImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 作者：LiuYunTao
 * 日期: 20:07 2020/11/26
 * 描述：
 */
@WebServlet("/message.do")
public class MessageServlet extends BaseServlet {
    MessageSerive messageSerive = null;

    public MessageServlet() {
        messageSerive = new MessageSeriveImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }

    public void queryList(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<Message> messages=messageSerive.queryMessageByToUid(user.getId());
        System.out.println(messages);
    }
}
