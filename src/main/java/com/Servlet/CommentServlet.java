package com.Servlet;

import com.Service.Imp.ArticleService;
import com.Service.Imp.CommentService;
import com.pojo.Article;
import com.pojo.Comment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentServlet {
    public static void submitComment(HttpServletRequest req , HttpServletResponse resp)
    {
        Article AM =  ArticleService.getService().getArticle(Integer.parseInt(req.getParameter("id")));
        int authorId = Integer.parseInt(req.getParameter("ownerId"));
        Comment comment = new Comment();
        comment.setAuthorId(authorId);
        comment.setArticleId(AM.getId());
        comment.setText(req.getParameter("text"));
        comment.setDatetime( new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(new Date()));
        CommentService.getService().add(comment);
        try {
            resp.sendRedirect("http://localhost:8080/JSP/article.jsp?id="+ req.getParameter("id") +"&ownerId="+ req.getParameter("ownerId") + "#down");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
