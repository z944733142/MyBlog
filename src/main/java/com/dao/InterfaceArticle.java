package com.dao;

import com.pojo.Article;
import com.pojo.Author;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceArticle {

    boolean submitArticle(Article AM);
    List getArticleList();
    List getArticleList(int id);
    Article getArticle(int id);
    boolean numbAdd(int id);
    boolean updateArticle (Article AM);
    boolean DeleteArticle(int id);

}
