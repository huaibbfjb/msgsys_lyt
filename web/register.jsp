<%--
  Created by IntelliJ IDEA.
  User: lintao
  Date: 2020/7/28
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css" rel="stylesheet">
        .wrapper {
            width: 500px;
            font-size: 14px;
            border: 1px black solid;
        }

        .wrapper .title {
            font-weight: bold;
            font-size: 18px;
            background-color: aliceblue;
            padding: 10px;
        }

        .wrapper div {
            padding: 5px;
            margin: 5px;
        }

        .red {
            color: red;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn1").bind("click",function () {
                alert(666);
                /*if($("#headpic").val()==""){
                    alert($("#username").val());
                    $("#username").val(123)
                    alert($("#username").val());
                    //return false;
                }*/
            });
        });
    </script>
</head>
<body>
<div class="wrapper">
    <div class="title">欢迎注册</div>
    <form action="<%=basePath%>user.do?action=register" method="post" enctype="multipart/form-data">
        <div>
            <%--<input type="hidden" name="action" value="register">--%>
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
            头像上传<span class="red">*</span>
            <%--multiple:可以多选 accept=".doc,.docx" --%>
            <input type="file" name="file" accept=".jpg,.png" id="headpic">
        </div>
        <div>
            <span><button type="submit" id="btn1">注册</button></span>
        </div>
    </form>
</div>
</body>
</html>
