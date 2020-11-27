<%--
  Created by IntelliJ IDEA.
  User: lintao
  Date: 2020/7/28
  Time: 10:47
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
    
    <script type="text/javascript">
        function toRegister() {
            window.location.href="register.jsp";
        }
        
    </script>
</head>
<body>
<div class="wrapper">
    <div class="title">欢迎进入短信息系统</div>
    <form action="<%=basePath%>user.do" method="post">
        <input name="action" type="hidden" value="login">
        <div>
            用户名<span class="red">*</span>
            <span><input type="text" name="username" id="username"></span>
        </div>
        <div>
            密码<span class="red">*</span>
            <span><input type="password" name="password" id="password"></span>
        </div>
        <div>
            验证码<span class="red">*</span>
            <span>
                <input type="text" name="code" id="code">
                <img src="/kaptcha.jpg"><br>
            </span>
        </div>
        <%--<div>--%>
            <%--头像上传<span class="red">*</span>--%>
            <%--&lt;%&ndash;multiple:可以多选 accept=".doc,.docx" &ndash;%&gt;--%>
            <%--<input type="file" name="file" accept=".jpg,.png">--%>
        <%--</div>--%>
        <div>
            <span><button type="submit">登陆</button></span>
            <span><button type="button" onclick="toRegister()">注册</button></span>
        </div>
    </form>
</div>

</body>
</html>
