package com.dao;

import com.pojo.Author;

import java.sql.SQLException;

public interface InterfaceAuthor {
    Author idDbCheck(int id) throws SQLException;
    Author pwdDbCheck(String uname, String pwd);
    boolean register(Author Author,  int sex, int address);
    boolean updateImg(int id, String path);
    boolean updateInfo(int id,String pwd, String addre, String message);
}
