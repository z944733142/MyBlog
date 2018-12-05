package com.Filter;

import com.Servlet.ArticleServlet;
import com.Servlet.AuthorServlet;
import com.Servlet.CommentServlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebFilter(filterName = "DistributeFilter" ,urlPatterns = {"/article/*", "/author/*", "/comment/*"})
public class DistributeFilter implements Filter {
    static final String articlePath = "com.Servlet.ArticleServlet";
    static final String authorPath = "com.Servlet.AuthorServlet";
    static final String commentPath = "com.Servlet.CommentServlet";
    Class<ArticleServlet> articleServletClass = null;
    Class<AuthorServlet> authorServletClass = null;
    Class<CommentServlet> commentServletClass = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            articleServletClass = (Class<ArticleServlet>) Class.forName(articlePath);
            authorServletClass = (Class<AuthorServlet>) Class.forName(authorPath);
            commentServletClass = (Class<CommentServlet>) Class.forName(commentPath);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String url = ((HttpServletRequest)servletRequest).getRequestURL().toString();
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");
        String mtd = null;
        Class aClass = null;
        Method method = null;
        int start = url.lastIndexOf("/") + 1;
        int end = url.indexOf("?");
        if(end != -1) {
            mtd = url.substring(start, end);
        }else{
            mtd = url.substring(start);
        }
        if(url.indexOf("article")!= -1)
        {
            aClass = articleServletClass;
        }else if(url.indexOf("author")!= -1)
        {
            aClass = authorServletClass;
        }
        else if(url.indexOf("comment")!= -1)
        {
            aClass = commentServletClass;
        }
        try {
            method = aClass.getMethod(mtd, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(null, servletRequest, servletResponse);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
