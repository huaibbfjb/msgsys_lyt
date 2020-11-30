package com.msgsys.servlet;

import com.google.gson.Gson;
import com.msgsys.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/demo01")
public class Demo01Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //JavaBean和json的互转
        //Person person = new Person(1, 15, "小刚", 'F');
        User user=new User(1,"lyt","123","123@qq.com");
        //创建Gson对象实例
        Gson gson = new Gson();
        //把person对象 转成JSON字符串
        String userJSONStr = gson.toJson(user);
        System.out.println(userJSONStr);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(userJSONStr);
    }
}
