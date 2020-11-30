package com.msgsys.servlet;


import com.msgsys.utils.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/imgUpload")
public class ImageUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //金句：防止中文乱码
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        //告诉浏览器这是一个JSON
        response.setContentType("application/json; charset=utf-8");
        String s = "{ \"errno\":0,\"data\":[ \"http://localhost:8083/upload/" + FileUtils.singleUpload(request, response).get("fileName") + "\"] }";
        response.getWriter().write(s);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
