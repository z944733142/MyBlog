package com.Service.Imp;



import com.Service.InterfaceAuthor;
import com.dao.imp.AuthorDao;
import com.pojo.Author;

import java.sql.SQLException;

public class AuthorService implements InterfaceAuthor {


    private static AuthorService service = null;

    private AuthorService() {
    }

    public static AuthorService getService() {
        if (service == null) {
            service = new AuthorService();
        }
        return service;
    }
    @Override
    public com.pojo.Author pwdCheck(String uname, String pwd) {
        AuthorDao authorDaoe =AuthorDao.getAuthorDao();
        return authorDaoe.pwdDbCheck(uname, pwd);
    }

    @Override
    public com.pojo.Author idCheck(int id) {
        AuthorDao authorDaoe =AuthorDao.getAuthorDao();
        return authorDaoe.idDbCheck(id);
    }


    @Override
    public boolean register(com.pojo.Author Author, int sex, int address) {
        return AuthorDao.getAuthorDao().register(Author, sex, address);
    }


    @Override
    public boolean updateImg(int id, String path) {
        return AuthorDao.getAuthorDao().updateImg(id, path);
    }

    @Override
    public boolean updateInfo(int id, String pwd, String addre, String message) {
        return AuthorDao.getAuthorDao().updateInfo(id, pwd, addre, message);
    }

}
