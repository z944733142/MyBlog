package com.Servlet;

import com.Service.Imp.AuthorService;
import com.pojo.Author;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class AuthorServlet {

    private static final long serialVersionUID = 1L;

    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "img";

    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    public static void ckLogin (HttpServletRequest req , HttpServletResponse resp) {
        Cookie cookies[] = req.getCookies();
        StringBuilder sb = new StringBuilder("");
        if(cookies != null)
        {
            for(int i = 0; i < cookies.length; i++)
            {
                if (cookies[i].getName().equals("uid"))
                {
                    sb.append(cookies[i].getValue());
                    break;
                }
            }
        }
        try {
            if(!sb.toString().equals(""))
            {
                Author Author = null;
                if((Author = AuthorService.getService().idCheck(Integer.parseInt(sb.toString())))!=null)
                {
                    req.getSession().setAttribute("Author", Author);

                        resp.sendRedirect("/JSP/main.jsp");

                }
                else {
                    resp.sendRedirect("/JSP/login.jsp");
                }
            }
            else
            {
                resp.sendRedirect("/JSP/login.jsp");
            }  } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exitLogin(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        Cookie CK = new Cookie("uid", "0");
        CK.setMaxAge(0);
        resp.addCookie(CK);
        req.getSession().setAttribute("Auther", null);
        resp.sendRedirect("/JSP/login.jsp");
    }

    public static void updateInfo(HttpServletRequest req , HttpServletResponse resp)
    {
        int id = ((Author)req.getSession().getAttribute("AuthorService")).getId();
        AuthorService.getService().updateInfo(id, req.getParameter("uaccount"), req.getParameter("pwd"), req.getParameter("msg"));
        req.getSession().setAttribute("Author", AuthorService.getService().idCheck(id));
        req.setAttribute("message", "修改成功");
        try {
            req.getRequestDispatcher("/JSP/Waiting.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void login(HttpServletRequest req , HttpServletResponse resp) {
        String uname = req.getParameter("uname");
        String upwd = req.getParameter("pwd");
        Author author = null;
        if((author = AuthorService.getService().pwdCheck(uname, upwd)) != null)
        {
            String a = new Integer(author.getId()).toString();
            Cookie CK = new Cookie("uid", a);
            req.getSession().setAttribute("Author", author);
            CK.setMaxAge(60*30);
            resp.addCookie(CK);
            try {
                resp.sendRedirect("/JSP/main.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            req.setAttribute("message", "账号和密码不匹配 ");
            req.setAttribute("theurl","/ckServlet");
            try {
                req.getRequestDispatcher("/JSP/Waiting.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void register(HttpServletRequest req , HttpServletResponse resp)  {
        Author Author = new Author();
        Author.setAccount(req.getParameter("uaccount"));
        Author.setName(req.getParameter("uname"));
        Author.setPassword(req.getParameter("pwd"));
        Author.setIntroduce(req.getParameter("msg"));
        if(AuthorService.getService().register(Author,Integer.parseInt(req.getParameter("sex")),  Integer.parseInt(req.getParameter("address"))))
        {
            req.setAttribute("message", "注册成功");
            req.setAttribute("theurl", "/JSP/login.jsp");
            try {
                req.getRequestDispatcher("/JSP/Waiting.jsp").forward(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Upload(HttpServletRequest request , HttpServletResponse response)
    {

        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = "E:\\myBlog\\src\\main\\webapp\\JSP" + File.separator + UPLOAD_DIRECTORY;


        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);

            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        request.setAttribute("message",
                                "文件上传成功!");
                        AuthorService.getService().updateImg(((Author)request.getSession().getAttribute("Author")).getId(),fileName);
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        // 跳转到 message.jsp
        try {
            request.getServletContext().getRequestDispatcher("/JSP/Waiting.jsp").forward(
                    request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
