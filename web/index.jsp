<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h2>Hello World!</h2>
    <form action="/fileUpload" method="post" enctype="multipart/form-data" >
        头像上传:
        <%--multiple:可以多选 accept=".doc,.docx" --%>
        <p><input type="file" name="file" multiple accept=".jpg,.png"></p>
        <p><input type="submit" value="submit"></p>
    </form>
</body>
</html>
