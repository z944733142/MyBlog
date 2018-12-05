package com.Service.Imp;

import com.Service.InterfaceComment;
import com.dao.imp.CommentDao;
import java.util.List;

public class CommentService implements InterfaceComment {
    private static CommentService service = null;
    private CommentService() {
    }

    public static CommentService getService()
    {
        if(service == null)
        {
            service = new CommentService();
        }
        return service;
    }
    @Override
    public boolean add(com.pojo.Comment comment) {
        return CommentDao.getCommentDao().add(comment);

    }

    @Override
    public List getList(int articleId) {
        return CommentDao.getCommentDao().getList(articleId);
    }
}
