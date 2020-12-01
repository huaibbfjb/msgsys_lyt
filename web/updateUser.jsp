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
        #div1 {
            margin: 0 auto;
            border: 1px solid #4c0000;
            width: 800px;
        }

        #form1 {
        }

        form div {
            margin-left: 10%;
            line-height: 30px;
        }

        form div span {
            color: red;
            font-size: 15px;
        }
    </style>
    <style>
        .red {
            color: red;
        }
    </style>
    <script type="text/javascript" src="js/jquery-1.7.2.js"></script>
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

            //用户名
            function AccountName() {
                var $accN = $("#username").val();
                if (!isEmptyOrBlank($accN)) {	//不为空
                    var $zz = /^[a-zA-Z]{1}\w{3,15}$/;
                    if (!$zz.test($accN)) {
                        $("#user_prompt").show();
                        return false;
                    } else {
                        return true;
                    }
                } else {			//为空
                    $("#user_prompt").show();
                    return false;
                }
            }

            //密码
            function password() {
                var $password = $("#password").val();
                if (!isEmptyOrBlank($password)) {
                    var $zz = /^\w{4,10}$/;
                    if (!$zz.test($password)) {
                        $("#pwd_prompt").show();
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    $("#pwd_prompt").show();
                    return false;
                }
            }

            //确认密码
            function confirmPassword() {
                var $password = $("#password").val();
                var $rePassWord = $("#repassword").val();
                //使用全等
                if (!($password === $rePassWord)) {
                    $("#repwd_prompt").show();
                    return false;
                }
            }

            //验证邮箱
            function mailbox() {
                var $mailbox = $("#email").val();
                if (!isEmptyOrBlank($mailbox)) {
                    var $mailRegular = /^\w{1,10}@\w{0,3}.\w{3}$/;
                    if (!$mailRegular.test($mailbox)) {
                        $("#email_prompt").show();
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return false;
                    $("#email_prompt").show();
                }
            }

            //用户名
            $("#username").bind("focus", function () {
                $("#user_prompt").hide();
            });
            $("#username").bind("blur", function () {
                AccountName();
            });
            //密码
            $("#password").bind("focus", function () {
                $("#pwd_prompt").hide();
            });
            $("#password").bind("blur", function () {
                password();
            });
            //确认密码
            $("#repassword").bind("focus", function () {
                $("#repwd_prompt").hide();
            });
            $("#repassword").bind("blur", function () {
                confirmPassword();
            });
            //邮箱确认
            $("#email").bind("focus", function () {
                $("#email_prompt").hide();
            });
            $("#email").bind("blur", function () {
                mailbox();
            });
            $('#chooseImage').bind('change', function () {
                var filePath = $(this).val(), //获取到input的value，里面是文件的路径
                    fileFormat = filePath.substring(filePath.lastIndexOf(".")).toLowerCase(),
                    src = window.URL.createObjectURL(this.files[0]); //转成可以在本地预览的格式
                // 检查是否是图片
                if (!fileFormat.match(/.png|.jpg|.jpeg/)) {
                    alert('上传错误,文件格式必须为：png/jpg/jpeg');
                    $('#cropedBigImg').attr('src', "images/default.png");
                    return false;
                } else {
                    $('#cropedBigImg').attr('src', src);
                }
            });
            $("#btn1").bind("click", function () {
                if (AccountName() == false || password() == false || confirmPassword() == false || mailbox() == false) {
                    return false;
                }
            });
        });
    </script>
</head>
<body>
<%
    //List<Message> messages = (List<Message>) request.getAttribute("messages");
    User user = (User) session.getAttribute("user");
%>
<div>
    <div id="div1">
        <h4 style="margin-left: 25%">
            当前用户:&nbsp;<%=user.getUsername()%>&nbsp;&nbsp;
            <img style="width: 45px;height: 45px;vertical-align: middle"
                 src="/fileDownload?path=<%=URLEncoder.encode(user.getImgPath(),"UTF-8")%>"/>
        </h4>
        <form action="<%=basePath%>user.do?action=updateByUserId" method="post" enctype="multipart/form-data"
              id="form1">
            <div>
                用户名<span class="red">*</span>
                <span><input type="text" name="username" id="username"></span>
                <span id="user_prompt">用户名由英文字母和数字组成的4-16位字符，以字母开头</span>
            </div>
            <div>
                新密码<span class="red">*</span>
                <span><input type="text" name="password" id="password"></span>
                <span id="pwd_prompt">密码由英文字母和数字组成的4-10位字符</span>
            </div>
            <div>
                确认新密码<span class="red">*</span>
                <span><input type="text" name="repassword" id="repassword"></span>
                <span id="repwd_prompt" style="display:none">两次密码不一致</span>
            </div>
            <div>
                邮箱<span class="red">*</span>
                <span><input type="text" name="email" id="email"></span>
                <span id="email_prompt" style="display:none">邮箱格式不正确</span>
            </div>
            <div>
                头像上传<span class="red">*</span>
                <input type="file" name="file" accept=".jpg,.png,.jpeg" id="chooseImage"><br>
                <img id="cropedBigImg" style="width: 200px;" src="images/default.png"/>
            </div>
            <div style="margin-left: 30%; margin-top: 40px">
                <span>
                    <button type="submit" id="btn1">确认修改</button>
                </span>
                <a href="<%=basePath%>message.do?action=queryList" style="margin-left: 30px">
                    <button type="button">返回</button>
                </a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
