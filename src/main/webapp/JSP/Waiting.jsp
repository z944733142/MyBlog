<%@ page import="static java.lang.Thread.sleep" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/3 0003
  Time: 上午 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <style>
        div{
            border: solid 1px ;
            margin: 15% auto;
            height: 20%;
            width: 20%;
            text-align: center;
        }
    </style>
</head>
<body>
    <div>
        <br>
        <br>
        <h3>${requestScope.message} 3秒后跳转</h3>
        <a href="/author/ckLogin">单击跳转</a>
    </div>
</body>
</html>
<%
    response.setHeader("Refresh","3;URL=/author/ckLogin");
%>
