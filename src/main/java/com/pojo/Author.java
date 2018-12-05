package com.pojo;

public class Author {
    private int id;
    private String account;
    private String name;
    private String password;
    private String introduce;
    private String sex;
    private String address;
    private String img;

    public Author(int id, String account, String name, String password, String introduce, String sex, String address, String img) {
        this.id = id;
        this.account = account;
        this.name = name;
        this.password = password;
        this.introduce = introduce;
        this.sex = sex;
        this.address = address;
        this.img = img;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getIntroduce() {
        return introduce;
    }

    public String getSex() {
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public String getImg() {
        return img;
    }

    public Author() {
    }
}
