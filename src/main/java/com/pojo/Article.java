package com.pojo;

public class Article {
    private int id;
    private int authorId;
    private int viewNum;
    private int commentNum;
    private String subtime;
    private String author;
    private String msg;
    private String title;
    private String htmlText;
    private String mkdText;

    public Article(int id, int authorId, int viewNum, int commentNum, String subtime, String author, String msg, String title, String htmlText, String mkdText) {
        this.id = id;
        this.authorId = authorId;
        this.viewNum = viewNum;
        this.commentNum = commentNum;
        this.subtime = subtime;
        author = author;
        this.msg = msg;
        this.title = title;
        this.htmlText = htmlText;
        this.mkdText = mkdText;
    }

    public void setSubtime(String subtime) {
        this.subtime = subtime;
    }

    public String getSubtime() {
        return subtime;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    public void setId(int id) {
        this.id = id;
    }





    public void setTitle(String title) {
        this.title = title;
    }



    public int getId() {
        return id;
    }





    public String getTitle() {
        return title;
    }


    public void setHtmlText(String htmlText) {
        this.htmlText = htmlText;
    }

    public void setMkdText(String mkdText) {
        this.mkdText = mkdText;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public String getMkdText() {
        return mkdText;
    }


    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getViewNum() {
        return viewNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public Article() {
    }
}
