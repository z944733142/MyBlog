<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>个人博客</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="/CSS/login.css">
</head>
<body style="background-color: mintcream">
<div>
<div style="position: absolute;top:19%;left: 48%;font-size: 100%; border: solid 1px">
</div>
<div id="loginDiv">
    <form action="/author/login" method="get">
        <br>
        <input type="text" name="uname">
        <br>
        <br>
        <input type="password" name="pwd">
        <br><br>

        <input type="submit" id = "loginsub"value="登录" style="font-size: 130%
">
    </form>
    <form action="/JSP/register.jsp">
        <input type="submit" id="resub" value="注册"  style="font-size: 90%;
">
    </form>
    <form action="/JSP/main.jsp">
        <input type="submit" id = "viewsub"value="游客"  style="font-size: 90%;
">
    </form>
</div>
</div>

<div style="position: absolute; left: 44%; top: 10%;"><h3 style="font-size: 400%;color: #0080FF">myBlog</h3></div>
 <div style="position: absolute;top: 32%;left: 48.5%;font-size: 150%">login</div>

</body>
</html>
