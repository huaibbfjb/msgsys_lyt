package com.msgsys.servlet;

import com.msgsys.entity.Message;
import com.msgsys.entity.User;
import com.msgsys.service.MessageSerive;
import com.msgsys.service.UserService;
import com.msgsys.service.impl.MessageSeriveImpl;
import com.msgsys.service.impl.UserServiceImpl;
import com.msgsys.utils.ConstData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 作者：LiuYunTao
 * 日期: 20:07 2020/11/26
 * 描述：
 */
@WebServlet("/message.do")
public class MessageServlet extends BaseServlet {
    MessageSerive messageSerive = null;
    UserService userService = null;

    public MessageServlet() {
        messageSerive = new MessageSeriveImpl();
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }
    //当前用户的邮件列表
    public void queryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Message> messages = messageSerive.queryMessageByToUid(user.getId());
        //System.out.println("messages:"+messages);
        request.setAttribute("messages", messages);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    //删除邮件
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer messageId = Integer.valueOf(id);
        int result = messageSerive.delete(messageId);
        //删除完毕更新列表
        queryList(request, response);
    }

    //查看邮件详情
    public void queryDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Integer messageId = Integer.valueOf(id);
        Message message = messageSerive.queryMessageById(messageId);
        Integer fromId = message.getFromUid();
        User user2 = userService.queryUserById(fromId);
        message.setReadFlag(ConstData.MESSAGE_ISREAD_READ);
        //System.out.println("查看邮件详情"+message);
        messageSerive.update(message);
        request.setAttribute("message", message);
        request.setAttribute("user2", user2);
        request.getRequestDispatcher("/details.jsp").forward(request, response);

    }

    //发送邮件
    public void send(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("mtitle");
        String email = request.getParameter("email");
        String mcontent = request.getParameter("mcontent");
        //发送给谁
        User toUser = userService.queryUserByEmail(email);
        String msg;
        if (toUser == null) {
            msg="email不存在！";
            //response.sendRedirect(getServletContext().getContextPath()+"/send.jsp");
        } else {
            Message message = new Message();
            message.setmTitle(title);
            //request.getSession()等于jsp中的session
            User fromUser = (User) request.getSession().getAttribute("user");
            message.setFromUid(fromUser.getId());
            message.setToUid(toUser.getId());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(ConstData.DATA_FORMAT_STR);
            message.setCreateTime(simpleDateFormat.format(new Date()));
            message.setmContent(mcontent);
            message.setReadFlag(ConstData.MESSAGE_ISREAD_UNREAD);
            int result = messageSerive.insert(message);
            if(result>0){
                msg="发送成功！";
            }else {
                msg="发送失败！";
            }
        }
        response.getWriter().write(msg);
      /*  request.setAttribute("msg",msg);
        request.getRequestDispatcher("/result.jsp").forward(request,response);*/
    }
}
