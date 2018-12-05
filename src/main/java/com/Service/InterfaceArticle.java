package com.Service;

import com.pojo.Article;
import com.pojo.Author;

import java.util.List;

public interface InterfaceArticle {

    boolean updateArticle (Article AM);



    boolean submitArticle(Article AM);

    List getArticleList();

    List getArticleList(int id);

    Article getArticle(int id);

    boolean numbAdd(int id);

    boolean DeleteArticle(int id);



}
