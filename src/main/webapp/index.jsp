<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName()+":" + request.getServerPort() + path + "/";
%>
<base href="<%=basePath%>">
<%
//    response.sendRedirect("/JSP/aaa.jsp");
    response.sendRedirect("/author/ckLogin");
%>