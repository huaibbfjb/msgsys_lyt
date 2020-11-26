package com.msgsys.servlet;

import com.msgsys.entity.User;
import com.msgsys.service.UserService;
import com.msgsys.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 作者：LiuYunTao
 * 日期: 18:29 2020/11/26
 * 描述：
 */
@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
    UserService userService=new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);
    }
    //注册
    public void register(HttpServletRequest request, HttpServletResponse response){
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        User user=new User(username,password,email);
        int result=userService.register(user);
        //System.out.println(result);
    }
    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InterruptedException {
        response.setContentType("text/html; charset=UTF-8");
        //获取验证码
        String token=(String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println("谷歌生成的验证码："+token);
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String code=request.getParameter("code");
        if(token!=null && token.equalsIgnoreCase(code)){//验证码正确
            User user=userService.login(new User(username,password,null));
            //System.out.println(user);
            if(user!=null){
                //登录成功
                HttpSession session=request.getSession();
                session.setAttribute("user",user);
                request.getRequestDispatcher("/message.do?action=queryList").forward(request,response);
                //response.sendRedirect(request.getContextPath()+"/list.jsp");
            }else {
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }else {
            //response.getWriter().print("验证码输入错误！两秒后跳转回登录页面！");
            //Thread.sleep(2000);
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }

    }
}
