<%@ page import="com.msgsys.entity.Message" %>
<%@ page import="com.msgsys.entity.User" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css" rel="stylesheet">
        body {
            margin: 0px;
            padding: 0px;

        }

        ul li {
            list-style-type: none;
            margin-bottom: 10px;
            border-bottom: 1px aliceblue dashed;
        }

        .wrapper {
            width: 800px;
            font-size: 14px;
            border: 1px black solid;
        }

        .wrapper .menu {
            width: 100%;
            float: right;
            background-color: aliceblue;
            padding: 10px;
        }

        .wrapper .menu span {
            margin-left: 5px;
        }

        .wrapper .menu span a {
            text-decoration: none;
            margin-left: 15px;
            color: cornflowerblue;
        }

        .wrapper div {
            padding: 5px;
            margin: 5px;
        }

        .rfloat {
            float: right;
        }

        .red {
            color: red;
        }

        .clear {
            clear: both;
        }

        ul li span {
            margin-right: 5px;
        }

        .bordstyle {
            font-weight: bolder;
        }

        .content {
            border: 1px gainsboro solid;
            background-color: azure;
        }

        .content-top span {
            margin-right: 10px;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
    <script type="text/javascript">
        $(function() {
            //判断字符串是否为空
            function isEmptyOrBlank(str) {
                if (str == null || str.length == 0) {
                    return true;
                } else {
                    return false;
                }
            }
            $("#btn1").bind("click",function () {
                if(isEmptyOrBlank($("#mtitle").val()) || isEmptyOrBlank($("#email").val()) ||
                    isEmptyOrBlank($("#mcontent").val())){
                    //alert("空！")
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<%
    Message message = (Message) request.getAttribute("message");
    User user = (User) session.getAttribute("user");
    User user2 = (User) request.getAttribute("user2");//request中保存的发送邮件的用户
%>
<div class="wrapper">
    <div class="header">
        <div class="menu">
            <span>
                当前用户：<%=user.getUsername()%>&nbsp;
                <img style="vertical-align: middle" width="40px;"
                     src="/fileDownload?path=<%=URLEncoder.encode(user.getImgPath(),"UTF-8")%>"/>
            </span>
            <span>
                <a href="send.jsp">发送消息</a>
                <a href="logout.jsp">退出</a>
                <a href="updateUser.jsp">修改信息</a>
                <a href="<%=basePath%>message.do?action=queryList">返回</a>
            </span>
        </div>
    </div>
    <div class="clear"></div>
    <div class="content">
        <form action="message.do?action=send" method="post">
            <div class="content-top">
            <span>
                标题：<input name="mtitle" type="text" id="mtitle">
            </span><br><br>
                <span>
                发送至邮件地址：<input name="email" type="text" id="email">
            </span><br>

            </div>
            <div class="content-body">
                消息内容:<br>
                <textarea name="mcontent" id="mcontent" style="width: 500px;height: 200px"></textarea>
            </div>
            <button type="submit" id="btn1">发送</button>
        </form>

    </div>
</div>
</body>
</html>
