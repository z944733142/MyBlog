<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.Service.Imp.ArticleService" %>
<%@ page import="com.pojo.Article" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if ( request.getParameter("id")!= null && !request.getParameter("id").equals("")) {
        Article am = ArticleService.getService().getArticle(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("Article", am);
    }


%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>Title</title>
    <link rel="stylesheet" href="/editormd/examples/css/style.css"/>
    <link rel="stylesheet" href="/editormd/css/editormd.css"/>
    <link type="text/css" href="/editormd/css/codemirror.min.css">
    <link rel="stylesheet" href="/editormd/css/editormd.css">
    <script language="JavaScript" src="/editormd/js/jquery-3.1.1.min.js"></script>
</head>
<body>
    <c:if test="${empty Article}">
        <form action="/article/markdone" method="post">
    </c:if>
        <c:if test="${Article != null}">
        <form action="/article/editor?id=${Article.id}" method="post">
        </c:if>
        <div id="titleDiv" style="font-size: 22px; margin: 20px">
            title <input type="text" name="title" style="height: 30px; width: 300px" value= <c:if test="${Article != null}">${Article.title}</c:if>>
        </div>
        <div class="editormd" id="test-editormd">
            <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc" style=";display: none"><c:if test="${Article != null}">${Article.mkdText}</c:if></textarea>
            <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
            <textarea id="my-editormd-html-code" name="my-editormd-html-code" style="display:none;"></textarea>
        </div>
        <div style="width:90%;margin: 10px auto;">
            <input type="submit" value="Submit" style="padding: 5px;">
        </div>
    </form>


    <script src="/editormd/examples/js/jquery.min.js"></script>
    <script src="/editormd/editormd.min.js"></script>
    <script type="text/javascript">
        $(function () {
            editormd("test-editormd", {
                width: "90%",
                height: 640,
                syncScrolling: "single",
                //你的lib目录的路径，我这边用JSP做测试的
                path: "/editormd/lib/",
                //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
                saveHTMLToTextarea: true
            });
        });

        // $(function() {
        //
        //          editormd("test-editormd", {
        //             width  : "90%",
        //             height : 640,
        //             path   : "/JSP/editormd/lib/",
        //             appendMarkdown : md,
        //             saveHTMLToTextarea : true
        //         });
        //
        //     //testEditor.getMarkdown();       // 获取 Markdown 源码
        //testEditor.getHTML();           // 获取 Textarea 保存的 HTML 源码
        //     //testEditor.getPreviewedHTML();  // 获取预览窗口里的 HTML，在开启 watch 且没有开启 saveHTMLToTextarea 时使用
        // });

    </script>
</body>

</html>
