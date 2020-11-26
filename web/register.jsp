<%--
  Created by IntelliJ IDEA.
  User: lintao
  Date: 2020/7/28
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="head.jsp"%>
<html>
<head>
    <title>Title</title>
    <style type="text/css" rel="stylesheet">
        .wrapper{
            width: 500px;
            font-size: 14px;
            border: 1px black solid ;
        }
        .wrapper .title{
            font-weight: bold;
            font-size: 18px;
            background-color: aliceblue;
            padding: 10px;
        }
        .wrapper div{
            padding: 5px;
            margin: 5px;
        }
        .red{
            color: red;
        }
    </style>
</head>
<body>
<div class="wrapper">
    <div class="title">欢迎注册</div>
    <form action="<%=basePath%>user.do">
        <input name="action" type="hidden" value="register">
        <div>
            用户名<span class="red">*</span>
            <span><input type="text" name="username" id="username"></span>
        </div>
        <div>
            密码<span class="red">*</span>
            <span><input type="password" name="password" id="password"></span>
        </div>
        <div>
            确认密码<span class="red">*</span>
            <span><input type="password" name="repassword" id="repassword"></span>
        </div>
        <div>
            邮箱<span class="red">*</span>
            <span><input type="text" name="email" id="email"></span>
        </div>
        <div>
            <span><button type="submit">注册</button></span>
        </div>
    </form>
</div>
</body>
</html>
