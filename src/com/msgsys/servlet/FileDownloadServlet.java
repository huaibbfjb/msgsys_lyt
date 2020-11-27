package com.msgsys.servlet;

import com.msgsys.utils.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/fileDownload")
public class FileDownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String path = request.getParameter("path");
        //System.out.println("path0:"+path);
        download(request, response, path);
    }

    public void download(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            FileUtils.singleDownload(request,response,path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
