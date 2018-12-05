package com.dao;

import com.pojo.Comment;

import java.util.List;

interface InterfaceComment {
    boolean add(com.pojo.Comment comment);
    List getList(int articleId);
}
