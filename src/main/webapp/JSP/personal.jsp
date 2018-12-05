<%@ page import="com.pojo.Author" %>
<%@ page import="com.pojo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.Service.Imp.ArticleService" %>
<%@ page import="com.Service.Imp.AuthorService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Author visitor = (Author) request.getSession().getAttribute("Author");
    Author author = AuthorService.getService().idCheck(Integer.parseInt(request.getParameter("ownerId")));
    List list = ArticleService.getService().getArticleList(Integer.parseInt(request.getParameter("ownerId")));
    request.setAttribute("list", list);
    request.setAttribute("author", author);
    request.setAttribute("visitor", visitor);
%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/CSS/main.css">
</head>
<body>

<div id="lead2">
    <div style="width:60%;position: relative; left: 20%; border: none">
        <h1><%
            if (author != null) {
        %>
            ${author.account}
            <%
                }
            %>
            博客主页</h1>
    </div>
</div>
<%
    if (visitor == null) {
        response.getWriter().write("        <ul>");
        response.getWriter().write("        <li>");
        response.getWriter().write("        <div id=\"top\">");
        response.getWriter().write("<div id = \"top1\">");
        response.getWriter().write("<a href=\"login.jsp\">登录</a>");
        response.getWriter().write("<a href=" + "register.jsp" + " style = \"margin-left: 30%;\">注册</a>");
        response.getWriter().write("        </div>");
        response.getWriter().write("        </div>");
    } else {
        response.getWriter().write("        <ul>");
        response.getWriter().write("        <li>");
        response.getWriter().write("        <div id=\"top\">");
        response.getWriter().write("<div id = \"top1\" >");
        response.getWriter().write("<a href=\"/JSP/personal.jsp?ownerId=" + visitor.getId() + "\">" + visitor.getAccount() + "</a>");
        response.getWriter().write("<a href=\"/author/exitLogin\" style = \"margin-left: 30%;\">退出</a>");
        response.getWriter().write("        </div>");
        response.getWriter().write("        </div>");
    }
%>
</li>
<li>
    <div id="left">
        <c:if test="${author != null}">
            <a href="upload.jsp"><img src="/JSP/img/${author.img}" alt=""></a>
        </c:if>
        <c:if test="${author == null}">
            <a href="/author/ckLogin"><img src="/JSP/img/0.jpg" alt=""></a>
        </c:if>
        <table>
            <tr>
                <td>昵称</td>
                <td>${visitor.account}</td>
            </tr>
            <tr>
                <td>性别</td>
                <td>${visitor.sex}</td>
            </tr>
            <tr>
                <td>籍贯</td>
                <td>${visitor.address}</td>
            </tr>
            <tr>
                <td>个人简介</td>
                <td>${visitor.introduce}</td>
            </tr>
        </table>
    </div>
</li>
<li>
    <div id="right">
        <c:forEach items="${list}" var="articleDao">
            <div style="margin: 2% 2%; border: none;text-align: center">
                <h3><a href="/JSP/article.jsp?id=${articleDao.id}&amp;ownerId=${author.id}" style="font-size: 135%; margin: 3% ">${articleDao.title}</a></h3>

                <div style="margin-left: 4%; border: none;text-align: start">
                    <p style="font-size: 105%; width: 85%;margin-left: 5%">${articleDao.msg}</p>
                </div>
                <p><a href="/JSP/personal.jsp?ownerId=${author.id}" style="margin-left: 45%; font-size: 95%">${articleDao.author}</a>&nbsp;&nbsp;&nbsp;
                        ${articleDao.viewNum}&nbsp;访问&nbsp; ${articleDao.commentNum}评论 &nbsp;&nbsp;&nbsp;${articleDao.subtime}
                </p><hr>
            </div>
        </c:forEach>
    </div>
</li>
<li>

    <%
        if (visitor == null) {
            response.getWriter().write(" <div id=\"right2\">");
            response.getWriter().write("<div style=\"margin-top: 200%; font-size: 110%; background-color: #2d78f4;height: 5%;text-align: center;\">");
            response.getWriter().write("<a href=\"login.jsp\" style=\"color: white; align-content: center;position: relative; top: 10%\">写文章</a>");
            response.getWriter().write("        </div>");
            response.getWriter().write("        <div style=\"font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;\">\n");
            response.getWriter().write("            <a href=\"/JSP/main.jsp\" style=\"color: white;position: relative; top: 10%\">返回首页</a>\n");
            response.getWriter().write("        </div>");
            response.getWriter().write("        <div style=\"font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;\">");
            response.getWriter().write("            <a href=\"#top\" style=\"color: white;position: relative; top: 10%\">回到顶部</a>");
            response.getWriter().write("        </div>");
            response.getWriter().write("    </div>");
        } else {
            response.getWriter().write(" <div id=\"right2\">");
            response.getWriter().write("<div style=\"margin-top: 200%; font-size: 110%; background-color: #2d78f4;height: 5%;text-align: center;\">");
            response.getWriter().write("<a href=\"editor.jsp\" style=\"color: white; align-content: center;position: relative; top: 10%\">写文章</a>");
            response.getWriter().write("        </div>");
            response.getWriter().write("        <div style=\"font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;\">\n");
            response.getWriter().write("<a href=\"/JSP/personal.jsp?ownerId="+ visitor.getId()+ "\" style=\"color: white;position: relative; top: 10%\">我的主页</a>");
            response.getWriter().write("        </div>");
            response.getWriter().write("        <div style=\"font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;\">\n");
            response.getWriter().write("            <a href=\"/JSP/main.jsp\" style=\"color: white;position: relative; top: 10%\">返回首页</a>\n");
            response.getWriter().write("        </div>");
            response.getWriter().write("        <div style=\"font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;\">");
            response.getWriter().write("            <a href=\"#top\" style=\"color: white;position: relative; top: 10%\">回到顶部</a>");
            response.getWriter().write("        </div>");

            response.getWriter().write("    </div>");

        }
    %>





</li>
</ul>
</body>
</html>