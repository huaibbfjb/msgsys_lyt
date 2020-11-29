<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="head.jsp" %>
<html>
<body>
<h2>Hello World!</h2>
<%--    <form action="/fileUpload" method="post" enctype="multipart/form-data" >
        头像上传:
        &lt;%&ndash;multiple:可以多选 accept=".doc,.docx" &ndash;%&gt;
        <p><input type="file" name="file" multiple accept=".jpg,.png"></p>
        <p><input type="submit" value="submit"></p>
    </form>--%>
<a href="<%=basePath%>user.do?action=queryAll">全部用户列表</a>
</body>
</html>
