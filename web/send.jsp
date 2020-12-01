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
    <script type="text/javascript" src="js/jquery-2.0.0.min.js"></script>
    <script type="text/javascript">
        $(function () {
            //判断字符串是否为空
            function isEmptyOrBlank(str) {
                if (str == null || str.length == 0) {
                    return true;
                } else {
                    return false;
                }
            }

            $("#btn1").bind("click", function () {
                if (isEmptyOrBlank($("#mtitle").val()) || isEmptyOrBlank($("#email").val())) {
                    //alert("空！")
                    return false;
                } else {
                    //获取富文本编辑器内容
                    //alert(editor.txt.html());
                    $.ajax({
                        url: "http://localhost:8083/message.do",
                        data: {
                            action: "send", mtitle: $("#mtitle").val(), email: $("#email").val(),
                            mcontent: editor.txt.html()
                        },
                        type: "GET",
                        dataType: "text",
                        success: function (data) {
                            alert(data)
                            /*$.ajax({
                                url: "http://localhost:8083/message.do?action=queryList"
                            });*/
                        }
                    });
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
        <form>
            <div class="content-top">
            <span>
                标题：<input name="mtitle" type="text" id="mtitle">
            </span><br><br>
                <span>
                发送至邮件地址：<input name="email" type="text" id="email">
            </span><br>
            </div>
            消息内容:<br>
            <div class="content-body">
                <p>欢迎使用 <b>wangEditor</b> 富文本编辑器</p>
            </div>
            <button type="button" id="btn1">发送</button>
        </form>
        <script type="text/javascript" src="https://unpkg.com/wangeditor/dist/wangEditor.min.js"></script>
        <script type="text/javascript">
            const E = window.wangEditor;
            const editor = new E('.content-body');
            // 或者 const editor = new E( document.getElementById('div1') )
            // 配置 server 接口地址
            editor.config.uploadImgServer = 'http://localhost:8083/imgUpload';
            editor.create();
        </script>
    </div>
</div>
</body>
</html>
