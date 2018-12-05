package com.Filter;

import com.Service.Imp.ArticleService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "numfilter" ,urlPatterns = "/JSP/article.jsp")
public class NumFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ArticleService.getService().numbAdd(Integer.parseInt(servletRequest.getParameter("id")));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
