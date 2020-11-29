<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.util.List" %>
<%@ page import="com.msgsys.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: WuLiangHang
  Date: 2020/11/27
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>全部用户列表</title>
    <style>
        td {
            font-size: 20px;
            border: 1px solid #a7b1bd;
            text-align: center;
        }
    </style>
</head>
<body>
<% List<User> users = (List<User>) request.getAttribute("users"); %>
<%--设置img的src属性 就可以让网页直接显示这张图片--%>
<%--<img src="/day1127/fileDownload"/>--%>
<table align="center">
    <tr>
        <td>ID</td>
        <td>用户名</td>
        <td>邮箱</td>
        <td>头像</td>
    </tr>

    <%
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
    %>
    <tr>
        <td><%=user.getId()%>
        </td>
        <td><%=user.getUsername()%>
        </td>
        <td><%=user.getEmail()%>
        </td>
        <%
            String ImgPath = user.getImgPath();
            //System.out.println("list2中的路径:"+ImgPath);
            if ("".equals(ImgPath) || ImgPath == null) {
                ImgPath = "E:\\upload\\default.jpg";
            }
        %>
        <%--金句：前端展示图片--%>
        <td>
            <img width="50px;" src="/fileDownload?path=<%=URLEncoder.encode(ImgPath,"UTF-8")%>"/>
        </td>

    </tr>
    <%
        }
    %>
</table>
</body>
</html>
