package com.dao.imp;

import com.Service.InterfaceComment;
import com.dao.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao implements InterfaceComment {
    private static CommentDao commentDao = null;

    private CommentDao() {

    }

    public static CommentDao getCommentDao() {
        if (commentDao == null) {
            commentDao = new CommentDao();
        }
        return commentDao;
    }

    @Override
    public boolean add(com.pojo.Comment comment) {
        Connection CN = null;
        Connection CN2 = null;
        PreparedStatement PS = null;
        PreparedStatement PS2 = null;
        String sql = "insert into comment ( articleid ,Authorid, time, main) values " +
                "(?,?,?,?)";
        String sql2 = "update article set commentnumb = ? where id = ?";
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setInt(1, comment.getArticleId());
            PS.setInt(2, comment.getAuthorId());
            PS.setString(3, comment.getDatetime());
            PS.setString(4, comment.getText());
            PS.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                PS.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            CN2 = DBUtil.getConnection();
            PS2 = CN2.prepareStatement(sql2);
            PS2.setInt(1, ((com.pojo.Article) ArticleDao.getArticleDao().getArticle(comment.getArticleId())).getCommentNum() + 1);
            PS2.setInt(2, comment.getArticleId());
            PS2.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                PS2.close();
                CN2.close();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;


    }

    @Override
    public List getList(int articleId) {
        Connection CN = null;
        PreparedStatement PS = null;
        String sql = "select * from comment,Author where comment.articleid = ? and Author.id = comment.Authorid order by time";
        List list = new ArrayList();
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setInt(1, articleId);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                com.pojo.Comment comment = new com.pojo.Comment();
                comment.setId(RS.getInt("id"));
                comment.setDatetime(RS.getString("time"));
                comment.setText(RS.getString("main"));
                comment.setAuthorId(RS.getInt("Authorid"));
                comment.setArticleId(RS.getInt("articleid"));
                comment.setAuthorName(RS.getString("account"));
                comment.setImg(RS.getString("img"));
                list.add(comment);
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
}
