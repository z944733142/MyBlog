<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/11 0011
  Time: 下午 17:06
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
            padding: 3%;
            border: solid 1px;
            width: 25%;
            margin: 7% auto;
        }

    </style>
</head>
<body>
<form action="/author/updateInfo" method="post">
    <div>
    <h2 align="center">修改个人信息</h2><br>
    <table style=" position: relative; left: 18%">
        <tr>
            <td width="10%">昵称</td>
            <td width="20%"><input type="text" name="uaccount"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="pwd"></td>
        </tr>
        <tr>
            <td>个人简介</td>
            <td>
                <textarea name="msg" cols="20%" rows="10%"></textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="提交修改" style="position: relative; right: 20%">
            </td>
        </tr>
    </table>
    </div>
</form>
</body>
</html>
