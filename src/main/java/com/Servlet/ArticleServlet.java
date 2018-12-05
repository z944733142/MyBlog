package com.Servlet;

import com.Service.Imp.ArticleService;

import com.pojo.Article;
import com.pojo.Author;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ArticleServlet {


    public static void editor (HttpServletRequest req , HttpServletResponse resp){
        String html = req.getParameter("test-editormd-html-code");
        String mkd = req.getParameter("test-editormd-markdown-doc");
        String title = req.getParameter("title");
        int id = Integer.parseInt(req.getParameter("id"));
        Article AM =  ArticleService.getService().getArticle(id);
        AM.setTitle(title);
        AM.setMkdText(mkd);
        AM.setHtmlText(html);
        ArticleService.getService().updateArticle(AM);
        req.setAttribute("message", "编辑成功");
        req.setAttribute("theurl", "/JSP/main.jsp");
            try {
                req.getRequestDispatcher("/JSP/Waiting.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void markdone(HttpServletRequest req , HttpServletResponse resp)
        {
            String html = req.getParameter("test-editormd-html-code");
            String mkd = req.getParameter("test-editormd-markdown-doc");
            String title = req.getParameter("title");
            Article AM = new Article();
            System.out.println(html + mkd + title);
            AM.setAuthorId(((Author)req.getSession().getAttribute("Author")).getId());
            AM.setTitle(title);
            AM.setMkdText(mkd);
            AM.setHtmlText(html);
            ArticleService.getService().submitArticle(AM);
            req.setAttribute("message", "提交成功");
            req.setAttribute("theurl","/JSP/main.jsp");
            try {
                req.getRequestDispatcher("/JSP/Waiting.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void delete(HttpServletRequest req , HttpServletResponse resp)  {
            int id = Integer.parseInt(req.getParameter("id"));
            if(ArticleService.getService().DeleteArticle(id))
            {
                req.setAttribute("message", "删除成功");
                try {
                    req.getRequestDispatcher("/JSP/Waiting.jsp").forward(req, resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


}
