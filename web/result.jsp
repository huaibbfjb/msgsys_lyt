<%--
  Created by IntelliJ IDEA.
  User: Hasee
  Date: 2020/11/29
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结果</title>
</head>
<body>
<%
    String msg = (String) request.getAttribute("msg");
%>
<%=msg%>
</body>
</html>
