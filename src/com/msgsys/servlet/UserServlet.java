package com.msgsys.servlet;

import com.msgsys.entity.User;
import com.msgsys.service.UserService;
import com.msgsys.service.impl.UserServiceImpl;
import com.sun.deploy.util.SessionState;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * 作者：LiuYunTao
 * 日期: 18:29 2020/11/26
 * 描述：
 */
@WebServlet("/user.do")
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防止中文乱码的金句
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防止中文乱码的金句
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        super.doGet(request, response);
    }

    //注册
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //防止中文乱码的金句
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //String username=request.getParameter("username");
        //System.out.println("username:"+username);
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
                        String str = fileItem.getFieldName();//获取键
                        if ("username".equals(str)) {
                            user.setUsername(fileItem.getString());//添加键对应的值
                        } else if ("password".equals(str)) {
                            user.setPassword(fileItem.getString());
                        } else if ("email".equals(str)) {
                            user.setEmail(fileItem.getString());
                        }
                    } else {
                        //上传的头像
                        System.out.println("表单项的name = " + fileItem.getFieldName());
                        System.out.println("上传的文件名:" + fileItem.getName());
                        if ("".equals(fileItem.getName())) {//未上传头像，默认为默认头像
                            user.setImgPath("E:\\upload\\default.png");
                            System.out.println("未上传头像，默认为默认头像!");
                        } else {
                            String reg = ".+(.JPEG|.jpeg|.JPG|.jpg)$";
                            Pattern pattern = Pattern.compile(reg);
                            Matcher matcher = pattern.matcher(fileItem.getName());
                            //System.out.println(matcher.find());
                            if(matcher.find()==false){//上传的文件不是图片格式
                                user.setImgPath("E:\\upload\\default.png");
                                System.out.println("上传的文件不是图片格式，默认为默认头像!");
                            }else {
                                String path = newpic(fileItem);
                                user.setImgPath(path);
                            }
                        }

                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(user);
            //用户名与邮箱对应的用户都不存在，再做注册操作
            if(userService.queryUserByUsername(user.getUsername())==null && userService.queryUserByEmail(user.getEmail())==null){
                if (userService.register(user) == 1) {
                    System.out.println("注册成功！");
                } else {
                    System.out.println("注册失败！");
                }
            }else {
                System.out.println("用户名或邮箱已经存在，注册失败！");
            }

        }
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    //登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InterruptedException {
        //防止中文乱码的金句
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //获取验证码
        String token = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println("谷歌生成的验证码：" + token);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        System.out.println("username:" + username);
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
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }

    }

    //用户列表
    public void queryAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //防止中文乱码的金句
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        List<User> users = userService.queryAll();
        request.setAttribute("users", users);
        System.out.println("users:"+users);
     /*   for (int i = 0; i < users.size(); i++) {
            System.out.println("getImgPath():"+users.get(i).getImgPath());
        }*/
        request.getRequestDispatcher("/list2.jsp").forward(request, response);

    }

    //更新
    public void updateByUserId(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //防止中文乱码的金句
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        //String username=request.getParameter("username");
        //System.out.println("username:"+username);
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
                        System.out.println("普通表单name=" + fileItem.getFieldName());
                        //参数UTF-8解决乱码
                        System.out.println("value=" + fileItem.getString());
                        String str = fileItem.getFieldName();//获取键
                        if ("username".equals(str)) {
                            user.setUsername(fileItem.getString());//添加键对应的值
                        } else if ("email".equals(str)) {
                            user.setEmail(fileItem.getString());
                        } else if ("password".equals(str)) {
                            user.setPassword(fileItem.getString());
                        }
                    } else {
                        //上传的头像
                        System.out.println("表单项的name=" + fileItem.getFieldName());
                        System.out.println("上传的文件名:" + fileItem.getName());
                        if ("".equals(fileItem.getName())) {//未上传头像，默认为默认头像
                            user.setImgPath("E:\\upload\\default.jpg");
                            System.out.println("未上传头像 ，默认为默认头像!");
                        } else {
                            String path = newpic(fileItem);
                            user.setImgPath(path);
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(user);
            HttpSession session = request.getSession();//获取保存的用户信息
            user.setId(((User) session.getAttribute("user")).getId());//提取要更改的用户的id
            if (userService.update(user) == 1) {
                System.out.println("更新成功！");
                session.setAttribute("user", user);//保存新的用户信息
                request.getRequestDispatcher("/message.do?action=queryList").forward(request, response);
            } else {
                System.out.println("更新失败！");
            }
        }
    }

    //更新头像
    private String newpic(FileItem fileItem) throws Exception {
        StringBuilder sb = new StringBuilder("E:\\upload\\");
        sb.append(new Date().getTime());
        sb.append(fileItem.getName());
        String path = sb.toString();
        fileItem.write(new File(path));
        return path;
    }
    //通过用户名查询
    public void queryUserByUsername(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //防止中文乱码的金句
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String username=request.getParameter("username");
        User user=userService.queryUserByUsername(username);
//        String message = (user == null) ? "<font color=\"green\">用户名可用!</font>" : "<font color=\"red\">用户名已存在</font>";
        String message = (user == null) ? "true" : "false";
        response.getWriter().write(message);
    }
    //通过邮箱查询
    public void queryUserByEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //防止中文乱码的金句
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String email=request.getParameter("email");
        User user=userService.queryUserByEmail(email);
        String message = (user == null) ? "<font color=\"green\">邮箱可用</font>" : "<font color=\"red\">邮箱已存在</font>";
        response.getWriter().write(message);
    }
}
