package com.Service;

import com.pojo.Author;

public interface InterfaceAuthor {
    Author pwdCheck(String uname, String pwd);

    Author idCheck(int id);

    boolean register(Author Author,  int sex, int address);
    boolean updateImg(int id, String path);

    boolean updateInfo(int id, String pwd, String addre, String message);
}
