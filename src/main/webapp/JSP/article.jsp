<%@ page import="com.pojo.Author" %>
<%@ page import="com.pojo.Article" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.Service.Imp.ArticleService" %>
<%@ page import="com.Service.Imp.CommentService" %>
<%@ page import="com.pojo.Comment" %>
<%@ page import="com.Service.Imp.AuthorService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
    Author author = (Author) request.getSession().getAttribute("Author");
    List list = ArticleService.getService().getArticleList();
    request.setAttribute("list", list);
    request.setAttribute("author", author);
    Author author2 = AuthorService.getService().idCheck(Integer.parseInt(request.getParameter("ownerId")));
    request.setAttribute("author2", author2);
    Article AM = ArticleService.getService().getArticle(Integer.parseInt(request.getParameter("id")));
    request.setAttribute("articleDao", AM);
    List commentList = CommentService.getService().getList(AM.getId());
    request.setAttribute("CL", commentList);
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
    <%--<link rel="stylesheet" href="/editormd/examples/css/style.css"/>--%>
    <link rel="stylesheet" href="/editormd/css/editormd.css"/>
    <%--<link rel="stylesheet" href="/editormd/css/style.css">--%>
    <link rel="stylesheet" href="/editormd/css/editormd.css">
    <link rel="shortcut icon" href="https://pandao.github.io/editor.md/favicon.ico" type="image/x-icon">
    <link type="text/css" rel="stylesheet" href="/editormd/css/codemirror.min.css">

    <style>

    </style>
</head>
<body>
<div id="lead2">
    <div style="width:60%;position: relative; left: 20%; border: none">
        <h1><%
            if (author != null) {
        %>
            ${author2.account}
            <%
                }
            %>
            博客主页</h1>
    </div>
</div>
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
                <td>${author.account}</td>
            </tr>
            <tr>
                <td>性别</td>
                <td>${author.sex}</td>
            </tr>
            <tr>
                <td>籍贯</td>
                <td>${author.address}</td>
            </tr>
            <tr>
                <td>个人简介</td>
                <td>${author.introduce}</td>
            </tr>
        </table>
    </div>
</li>
<%
    if (author == null) {
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
        response.getWriter().write("<a href=\"/JSP/personal.jsp?ownerId=" + author.getId() + "\">" + author.getAccount() + "</a>");
        response.getWriter().write("<a href=\"/author/exitLogin\" style = \"margin-left: 30%;\">退出</a>");
        response.getWriter().write("        </div>");
        response.getWriter().write("        </div>");
    }
%>

</li>
<li>
    <div id="right" style="border: none;text-align: center">
        <div>
            <h2 style="font-size: 135%">${articleDao.title}</h2>
            <c:if test="${author.id == articleDao.authorId}">
                <p style="margin-right: 5%;color: #0080FF"><a href="editor.jsp?id=${articleDao.id}">编辑</a><a
                        href="delete.jsp?id=${articleDao.id}" style="color: #0080FF">删除</a></p>
            </c:if>
            <hr width="90%">

            <div class="editormd-html-textarea" id="content"
                 style="align-content: left; width: 95%;border: none">${articleDao.htmlText}</div>
            <script src="/editormd/js/jquery-3.1.1.min.js"></script>
            <script src="/editormd/js/marked.min.js"></script>
            <script src="/editormd/js/prettify.min.js"></script>
            <script src="/editormd/js/editormd.min.js"></script>
            <script type="text/javascript">
                editormd.markdownToHTML("content");
            </script>
            <div style="width: 100%;border: none">
                <hr width="100%">
                <h3>评论区</h3>
                <c:forEach items="${CL}" var="comment">
                    <div style="text-align: start;border: none">
                        <hr width="90%">

                        <h2 style="position: relative; top: 10%;left: 12%"><img src="/JSP/img/${comment.img}" alt="" style="margin: 0%;height: 4%; width: 4%">&nbsp;&nbsp;${comment.authorName}
                        </h2>
                        <p style="margin-left: 13%; margin-right: 13%">
                            ${comment.text}</p>
                        <div style="margin-right: 10%;text-align: end;border: none">${comment.datetime}</div>
                    </div>
                </c:forEach>
                <hr width="90%">

                <form action="/comment/submitComment">
                    <h3 style="margin-left: 10%">请撰写评论:</h3>
                    <textarea name="text" cols="100%" rows="10%" style="margin-left: 10%;margin-right: 3%;margin-top: 1%"></textarea>
                    <input type="text" style="display: none" name="id" value="${articleDao.id}">
                    <input type="text" style="display: none" name="ownerId" value="${author.id}">
                    <input type="submit" value="提交">

                </form>
                <a name="down"></a>
            </div>
            <p><a href="" style="margin-left: 60%; font-size: 95%">${articleDao.author}</a>&nbsp;&nbsp;&nbsp;
                ${articleDao.viewNum}&nbsp;访问&nbsp; &nbsp;&nbsp;&nbsp;${articleDao.subtime}
            </p>
        </div>

    </div>

</li>
<li>

            <c:if test="${author == null}">
              <div id= "right2">
             <div style= "margin-top: 200%; font-size: 110%; background-color: #2d78f4;height: 5%;text-align: center; ">
             <a href= "login.jsp " style= "color: white; align-content: center;position: relative; top: 10% ">写文章</a>
                     </div>
                     <div style= "font-size: 100%;background-color: #2d78f4;height:5%;text-align: center; ">
                         <a href= "/JSP/main.jsp " style= "color: white;position: relative; top: 10% ">返回首页</a>
             </div>
                     <div style= "font-size: 100%;background-color: #2d78f4;height:5%;text-align: center; ">
                         <a href= "#top " style= "color: white;position: relative; top: 10% ">回到顶部</a>
                     </div>
                 </div>
            </c:if>
            <c:if test="${author != null}">
              <div id= "right2">
             <div style= "margin-top: 200%; font-size: 110%; background-color: #2d78f4;height: 5%;text-align: center; ">
             <a href= "editor.jsp " style= "color: white; align-content: center;position: relative; top: 10% ">写文章</a>
                     </div>
                     <div style= "font-size: 100%;background-color: #2d78f4;height:5%;text-align: center; ">
             <a href= "/JSP/personal.jsp?ownerId=${author2.id}" style= "color: white;position: relative; top: 10% ">我的主页</a>
                     </div>
                     <div style= "font-size: 100%;background-color: #2d78f4;height:5%;text-align: center; ">
                         <a href= "/JSP/main.jsp " style= "color: white;position: relative; top: 10% ">返回首页</a>
             </div>
                     <div style= "font-size: 100%;background-color: #2d78f4;height:5%;text-align: center; ">
                         <a href= "#top " style= "color: white;position: relative; top: 10% ">回到顶部</a>
                     </div>
                 </div>
</c:if>
</li>
</ul>
</body>
<script src="/editormd/examples/js/jquery.min.js"></script>
<script src="/editormd/editormd.min.js"></script>
<%--<div style="margin-top: 200%; font-size: 110%; background-color: #2d78f4;height: 5%;text-align: center;"><a--%>
<%--href="editor.jsp" style="color: white; align-content: center;position: relative; top: 10%">写文章</a></div>--%>
<%--<div style="font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;">--%>
<%--<a href="/JSP/personal.jsp?ownerId=2" style="color: white;position: relative; top: 10%">我的主页</a></div>--%>
<%--<div style="font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;">--%>
<%--<a href="/JSP/main.jsp" style="color: white;position: relative; top: 10%">返回首页</a>--%>
<%--</div>--%>
<%--<div style="font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;"><a href="#top"--%>
<%--style="color: white;position: relative; top: 10%">回到顶部</a>--%>
<%--</div>--%>
<%--</div>--%>
<%--ws="10%"--%>
<%--style="margin-left: 10%;margin-right: 3%;margin-top: 1%">--%>
<%--<div style="margin-top: 200%; font-size: 110%; background-color: #2d78f4;height: 5%;text-align: center;"><a--%>
        <%--href="editor.jsp" style="color: white; align-content: center;position: relative; top: 10%">写文章</a></div>--%>
<%--<div style="font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;">--%>
    <%--<a href="/JSP/personal.jsp?ownerId=1" style="color: white;position: relative; top: 10%">我的主页</a></div>--%>
<%--<div style="font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;">--%>
    <%--<a href="/JSP/main.jsp" style="color: white;position: relative; top: 10%">返回首页</a>--%>
<%--</div>--%>
<%--<div style="font-size: 100%;background-color: #2d78f4;height:5%;text-align: center;"><a href="#top"--%>
                                                                                        <%--style="color: white;position: relative; top: 10%">回到顶部</a>--%>
<%--</div>--%>
<%--</div>                       style="margin-left: 10%;margin-right: 3%;margin-top: 1%">--%>
<%--ea name="text" cols="100%" rows="10%" style="margin-left: 10%;margin-right: 3%;margin-top: 1%">--%>
</html>

