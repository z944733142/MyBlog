package com.pojo;

public class Comment {
    private int id;
    private int authorId;
    private int articleId;
    private String authorName;
    private String img;
    private String datetime;
    private String text;

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }


    public void setImg(String img) {
        this.img = img;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getArticleId() {
        return articleId;
    }


    public String getImg() {
        return img;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getText() {
        return text;
    }

    public Comment() {
    }
}
