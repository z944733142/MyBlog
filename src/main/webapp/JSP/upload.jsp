<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>上传头像</title>
</head>
<body>
<div style="margin: 15% auto;text-align: center">
    <table style="border: solid 1px;position:relative; left: 40%">
  <tr><td><h1>上传头像</h1></td></tr>
<form method="post" action="/author/Upload" enctype="multipart/form-data">
    <tr><td><p>请选择一个图片</p> </td></tr>
    <tr><td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="file" name="uploadFile" /></td></tr>
    <tr><td><br></td></tr>
    <tr><td> <input type="submit" value="上传" /></td></tr>
    <tr><td><br><br></td></tr>
</form>
    </table>
</div>
</body>
</html>