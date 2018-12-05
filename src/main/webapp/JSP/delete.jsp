<%@ page import="com.Service.Imp.ArticleService" %>
<%@ page import="com.pojo.Article" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/11 0011
  Time: 下午 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Article AM = ArticleService.getService().getArticle(Integer.parseInt(request.getParameter("id")));
    request.setAttribute("Article", AM);
%>
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
            <p>确定删除${Article.title}?</p>
            <br>
            <a href="/article/delete?id=${Article.id}">确定</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="javascript:history.back(-1);">取消</a>
        </div>
</body>
</html>
