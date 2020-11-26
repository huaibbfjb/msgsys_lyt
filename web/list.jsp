<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    </style>
</head>
<body>
<div class="wrapper">
    <div class="header">
        <div class="menu">
            <span>当前用户：</span><span></span>
            <span><a href="#">发送消息</a><a href="logout.jsp">退出</a></span>
        </div>
    </div>
    <div class="clear"></div>
    <div class="content">
        <ul>
            <li>
                    <span>

                            <img src="images/read.jpg" width="30px" height="20px">


                        <img src="images/read2.jpg" width="30px" height="20px">

                    </span>
                <span class="bordstyle"></span><span><a href="msg.do?oper=query&id="></a></span>
                <span class="rfloat">
                        <span><a href="#">删除</a></span>
                        <span><a href="#">回信</a></span>
                        <span></span>
                    </span>
            </li>

        </ul>

    </div>
</div>
</body>
</html>
