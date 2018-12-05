package com.dao.imp;

import com.dao.DBUtil;
import com.dao.InterfaceArticle;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class ArticleDao implements InterfaceArticle {

    private static ArticleDao articleDao = null;
    private static List<String> sex = new ArrayList();
    private static List<String> address = new ArrayList();

    static {
        sex.add("女");
        sex.add("男");
        address.add("陕西");
        address.add("陕西");
        address.add("山西");
        address.add("唐山");
    }

    private ArticleDao() {

    }

    public static ArticleDao getArticleDao() {
        if (articleDao == null) {
            articleDao = new ArticleDao();
        }
        return articleDao;
    }

    @Override
    public boolean submitArticle(com.pojo.Article AM) {
        Connection CN = null;
        PreparedStatement PS = null;
        String sql = "insert into article ( Authorid, subtime, numb, title,htmltext, mkdtext) values " +
                "(?,?,?,?,?,?)";
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setInt(1, AM.getAuthorId());
            PS.setString(2, new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(new Date()));
            PS.setInt(3, 0);
            PS.setString(4, AM.getTitle());
            PS.setString(5, AM.getHtmlText());
            PS.setString(6, AM.getMkdText());
            PS.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                PS.close();
                CN.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List getArticleList() {

        Connection CN = null;
        PreparedStatement PS = null;
        String sql = "select article.id,article.Authorid, article.subtime, article.numb, article.title, article.htmltext, article.mkdtext, article.commentnumb, Author.account, Author.id from Author, article where article.Authorid =  Author.id order by subtime DESC";
        List list = new ArrayList();
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                com.pojo.Article am = new com.pojo.Article();
                String html = RS.getString("htmltext");
                am.setId(RS.getInt("id"));
                am.setAuthorId(RS.getInt("Authorid"));
                am.setSubtime(RS.getString("subtime").toString());
                am.setViewNum(RS.getInt("numb"));
                am.setTitle(RS.getString("title"));
                am.setHtmlText(html);
                am.setCommentNum(RS.getInt("commentnumb"));
                am.setAuthor(RS.getString("account"));
                am.setMkdText(RS.getString("mkdtext"));
                String msg = Html2Text.getContent(html);
                am.setMsg(msg.substring(0, msg.length() > 250 ? 250 : msg.length()).concat("..."));
                list.add(am);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                PS.close();
                CN.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList();
    }

    @Override
    public List getArticleList(int id) {

        Connection CN = null;
        PreparedStatement PS = null;
        String sql = "select article.id,article.Authorid, article.subtime, article.numb, article.title, article.commentnumb,article.htmltext, article.mkdtext, Author.account, Author.id from Author, article where article.Authorid =  Author.id and article.Authorid = ? order by subtime";
        List list = new ArrayList();
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setInt(1, id);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                com.pojo.Article am = new com.pojo.Article();
                String html = RS.getString("htmltext");
                am.setId(RS.getInt("id"));
                am.setAuthorId(RS.getInt("Authorid"));
                am.setSubtime(RS.getString("subtime"));
                am.setViewNum(RS.getInt("numb"));
                am.setTitle(RS.getString("title"));
                am.setHtmlText(html);
                am.setCommentNum(RS.getInt("commentnumb"));
                am.setAuthor(RS.getString("account"));
                am.setMkdText(RS.getString("mkdtext"));
                String msg = Html2Text.getContent(html);
                am.setMsg(msg.substring(0, msg.length() > 250 ? 250 : msg.length()).concat("..."));
                list.add(am);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                PS.close();
                CN.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList();
    }

    @Override
    public com.pojo.Article getArticle(int id) {
        Connection CN = null;
        PreparedStatement PS = null;
        com.pojo.Article AM = new com.pojo.Article();
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement("Select * from article where id = ?");
            PS.setInt(1, id);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                com.pojo.Article am = new com.pojo.Article();
                String html = RS.getString("htmltext");
                am.setId(RS.getInt("id"));
                am.setAuthorId(RS.getInt("Authorid"));
                am.setSubtime(RS.getString("subtime"));
                am.setViewNum(RS.getInt("numb"));
                am.setTitle(RS.getString("title"));
                am.setHtmlText(html);
                am.setCommentNum(RS.getInt("commentnumb"));
                am.setMkdText(RS.getString("mkdtext"));
                return am;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                PS.close();
                CN.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    public boolean numbAdd(int id) {
        Connection CN = null;
        PreparedStatement PS = null;
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement("update article set numb = ? where id = ?");
            PS.setInt(1, articleDao.getArticle(id).getViewNum() + 1);
            PS.setInt(2, id);
            PS.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                PS.close();
                CN.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateArticle(com.pojo.Article AM) {
        Connection CN = null;
        PreparedStatement PS = null;
        String sql = "update article set title=? ,htmltext=?, mkdtext=? where id = ?";
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setInt(4, AM.getId());
            PS.setString(1, AM.getTitle());
            PS.setString(2, AM.getHtmlText());
            PS.setString(3, AM.getMkdText());
            PS.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                PS.close();
                CN.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean DeleteArticle(int id) {
        Connection CN = null;
        PreparedStatement PS = null;
        String sql = "delete from article where id = ?";
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setInt(1, id);
            PS.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                PS.close();
                CN.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
