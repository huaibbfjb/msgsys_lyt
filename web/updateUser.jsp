<%@ page import="com.msgsys.entity.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="com.msgsys.entity.User" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: Hasee
  Date: 2020/11/27
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp" %>
<html>
<head>
    <title>修改个人信息</title>
    <style>
        #div1{
            margin: 0 auto;
            border: 1px solid #4c0000;
            width: 500px;
        }
        #form1{
        }
        form div{
            margin-left: 20%;
            line-height: 30px;
        }
    </style>
</head>
<body>
<%
    //List<Message> messages = (List<Message>) request.getAttribute("messages");
    User user = (User) session.getAttribute("user");
%>
<div>
    <div id="div1">
        <h4 style="margin-left: 25%">
            当前用户:<%=user.getUsername()%>&nbsp;&nbsp;&nbsp;
            id:<%=user.getId()%>&nbsp;&nbsp;
            <img style="vertical-align: middle" width="35px;"
                 src="/fileDownload?path=<%=URLEncoder.encode(user.getImgPath(),"UTF-8")%>"/>
        </h4>
        <form action="<%=basePath%>user.do?action=updateByUserId" method="post" enctype="multipart/form-data" id="form1">
            <div>
                用户名<span class="red">*</span>
                <span><input type="text" name="username" id="username"></span>
            </div>
            <div>
                新密码<span class="red">*</span>
                <span><input type="text" name="password" id="password"></span>
            </div>
            <div>
                确认新密码<span class="red">*</span>
                <span><input type="text" name="repassword" id="repassword"></span>
            </div>
            <div>
                邮箱<span class="red">*</span>
                <span><input type="text" name="email" id="email"></span>
            </div>
            <div>
                头像上传<span class="red">*</span>
                <input type="file" name="file" accept=".jpg,.png">
            </div>
            <div style="margin-left: 40%; margin-top: 40px">
                <span><button type="submit" id="btn1">确认修改</button></span>
            </div>
        </form>
    </div>
</div>
</body>
</html>
