package com.Service;

import com.pojo.Comment;

import java.util.List;

public interface InterfaceComment {
    boolean add(com.pojo.Comment comment);
    List getList(int articleId);
}
