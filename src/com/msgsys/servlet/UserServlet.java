package com.msgsys.servlet;

import com.msgsys.entity.User;
import com.msgsys.service.UserService;
import com.msgsys.service.impl.UserServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 作者：LiuYunTao
 * 日期: 18:29 2020/11/26
 * 描述：
 */
@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //防止中文乱码的金句
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //1 先判断上传的数据是否多段数据 （只有是多段的数据，才是文件上传的）
        if (ServletFileUpload.isMultipartContent(request)) {
            // 创建FileItemFactory 工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
            //创建用于解析上传数据的工具类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            User user = new User();
            try {
                //解析上传的数据 得到每一个表单项FileItem
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //循环判断，每一个表单项 是普通类型 还是上传的文件
                for (FileItem fileItem : list) {
                    if (fileItem.isFormField()) {
                        //普通表单项
                        System.out.println("普通表单项 name = " + fileItem.getFieldName());
                        //参数UTF-8解决乱码
                        System.out.println("value = " + fileItem.getString());
                        String str = fileItem.getFieldName();
                        if ("username".equals(str)) {
                            user.setUsername(fileItem.getString());
                        } else if ("password".equals(str)) {
                            user.setPassword(fileItem.getString());
                        } else if ("email".equals(str)) {
                            user.setEmail(fileItem.getString());
                        }
                    } else {
                        //上传的头像
                        System.out.println("表单项的name = " + fileItem.getFieldName());
                        System.out.println("上传的文件名:" + fileItem.getName());
                        StringBuilder sb = new StringBuilder("E:\\upload\\");
                        sb.append(new Date().getTime());
                        sb.append(fileItem.getName());
                        String path = sb.toString();
                        user.setImgPath(path);
                        fileItem.write(new File(path));
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(user);
            if(userService.register(user)==1){
                System.out.println("注册成功！");
            }else {
                System.out.println("注册失败！");
            }
        }

    }
    
    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InterruptedException {
        response.setContentType("text/html; charset=UTF-8");
        //获取验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println("谷歌生成的验证码：" + token);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        if (token != null && token.equalsIgnoreCase(code)) {//验证码正确
            User user = userService.login(new User(username, password, null));
            //System.out.println(user);
            if (user != null) {
                //登录成功
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getRequestDispatcher("/message.do?action=queryList").forward(request, response);
                //response.sendRedirect(request.getContextPath()+"/list.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }
        } else {
            //response.getWriter().print("验证码输入错误！两秒后跳转回登录页面！");
            //Thread.sleep(2000);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }
}
