<%--
  Created by IntelliJ IDEA.
  User: Hasee
  Date: 2020/11/26
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StringBuilder basePath = new StringBuilder();
    basePath.append(request.getScheme()).append("://").append(request.getServerName()).
            append(":").append(request.getServerPort()).append(request.getContextPath()).append("/");
//    String basePath=request.getScheme()+"://"+request.getServerName()+":"+
//            request.getServerPort()+request.getContextPath()+"/";
%>
<html>

</html>
