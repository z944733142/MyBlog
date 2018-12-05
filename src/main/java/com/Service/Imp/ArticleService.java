package com.Service.Imp;

import com.Service.InterfaceArticle;
import com.dao.imp.ArticleDao;

import java.util.List;

public class ArticleService implements InterfaceArticle {

    private static ArticleService service = null;

    private ArticleService() {
    }

    public static ArticleService getService() {
        if (service == null) {
            service = new ArticleService();
        }
        return service;
    }

    @Override
    public boolean updateArticle(com.pojo.Article AM) {
        return ArticleDao.getArticleDao().updateArticle(AM);
    }


    @Override
    public boolean submitArticle(com.pojo.Article AM) {
        return ArticleDao.getArticleDao().submitArticle(AM);
    }

    @Override
    public List getArticleList() {
        return ArticleDao.getArticleDao().getArticleList();
    }

    @Override
    public List getArticleList(int id) {
        return ArticleDao.getArticleDao().getArticleList(id);
    }

    @Override
    public com.pojo.Article getArticle(int id) {
        return ArticleDao.getArticleDao().getArticle(id);
    }

    @Override
    public boolean numbAdd(int id) {
        return ArticleDao.getArticleDao().numbAdd(id);
    }

    @Override
    public boolean DeleteArticle(int id) {
        return ArticleDao.getArticleDao().DeleteArticle(id);
    }

}
