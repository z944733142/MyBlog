package com.Listener;


import com.Service.Imp.ArticleService;

import javax.servlet.*;

//@WebListener
public class aListener implements ServletRequestListener{

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
            if (servletRequestEvent.getServletRequest().getParameter("id")!=null &&!servletRequestEvent.getServletRequest().getParameter("id").equals("") && servletRequestEvent.getServletRequest().getParameter("ownerId")!= null &&!servletRequestEvent.getServletRequest().getParameter("ownerId").equals(""))
            {
                int id = Integer.parseInt(servletRequestEvent.getServletRequest().getParameter("id"));
                ArticleService.getService().numbAdd(id);
            }
    }
}
