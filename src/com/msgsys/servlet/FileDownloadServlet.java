package com.msgsys.servlet;

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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String path = request.getParameter("path");
        //System.out.println("path0:"+path);
        download(request, response, path);
    }
    //用于显示邮件列表的用户头像
    public void download(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("image/jpeg");
        //金句：下载文件中文乱码解决
        // 把中文名进行UTF-8 编码操作。
        //System.out.println("path:"+path);
        //String str = "attachment; fileName=" + URLEncoder.encode(path, "UTF-8");
        // 然后把编码后的字符串设置到响应头中
        //金句：文件下载
        //response.setHeader("Content-Disposition", str);
        //System.out.println("str:"+str);
        //如果要读取工程外部 硬盘里的资源 建议使用FileInputStream 以及 BufferedInputStream
        InputStream fileIn = new FileInputStream(new File(path));
        InputStream in = new BufferedInputStream(fileIn);
        //使用ServletContext获取输入流 只能获取到工程内部的资源
        //InputStream in = getServletContext().getResourceAsStream();
        OutputStream out = response.getOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = in.read(buff)) > -1) {
            out.write(buff, 0, len);
            out.flush();
        }
        out.close();
        in.close();
    }
}
