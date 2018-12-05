package com.dao.imp;

import com.dao.DBUtil;
import com.dao.InterfaceAuthor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao implements InterfaceAuthor {
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

    private static AuthorDao authorDao = null;

    private AuthorDao() {

    }

    public static AuthorDao getAuthorDao() {
        if (authorDao == null) {
            authorDao = new AuthorDao();
        }
        return authorDao;
    }

    @Override
    public com.pojo.Author idDbCheck(int id) {
        Connection CN = null;
        PreparedStatement PS = null;
        com.pojo.Author Author = new com.pojo.Author();
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement("Select * from Author");
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                if (RS.getInt("id") == id) {
                    Author.setId(RS.getInt("id"));
                    Author.setAccount(RS.getString("account"));
                    Author.setName(RS.getString("name"));
                    Author.setPassword(RS.getString("pwd"));
                    Author.setSex(sex.get(RS.getInt("sex")));
                    Author.setAddress(address.get(RS.getInt("address")));
                    Author.setIntroduce(RS.getString("introduce"));
                    Author.setImg(RS.getString("img"));
                    return Author;
                }
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
    public com.pojo.Author pwdDbCheck(String name, String pwd) {
        String sql = "select * from Author where name = ? and pwd = ?";
        PreparedStatement PS = null;
        Connection CN = null;
        com.pojo.Author Author = new com.pojo.Author();
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setString(1, name);
            PS.setString(2, pwd);
            ResultSet RS = PS.executeQuery();
            while (RS.next()) {
                Author.setId(RS.getInt("id"));
                Author.setAccount(RS.getString("account"));
                Author.setName(RS.getString("name"));
                Author.setPassword(RS.getString("pwd"));
                Author.setSex(sex.get(RS.getInt("sex")));
                Author.setAddress(address.get(RS.getInt("address")));
                Author.setIntroduce(RS.getString("introduce"));
                Author.setImg(RS.getString("img"));
                return Author;
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
    public boolean register(com.pojo.Author Author, int sex, int address) {
        Connection CN = null;
        PreparedStatement PS = null;
        String sql = "insert into Author (account, name, pwd, sex, address, introduce) values " +
                "(?,?,?,?,?,?)";
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setString(1, Author.getAccount());
            PS.setString(2, Author.getName());
            PS.setString(3, Author.getPassword());
            PS.setInt(4, sex);
            PS.setInt(5, address);
            PS.setString(6, Author.getIntroduce());
            PS.execute();

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
        return false;
    }
    @Override
    public boolean updateImg(int id, String path) {
        Connection CN = null;
        PreparedStatement PS = null;
        String sql = "update Author set img=? where id = ?";
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setString(1, path);
            PS.setInt(2, id);
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
    public boolean updateInfo(int id,String account, String pwd, String message) {
        Connection CN = null;
        PreparedStatement PS = null;
        String sql = "update Author set account=?,pwd=?,introduce=? where id = ?";
        try {
            CN = DBUtil.getConnection();
            PS = CN.prepareStatement(sql);
            PS.setString(1, account);
            PS.setString(2, pwd);
            PS.setString(3,message);
            PS.setInt(4,id);
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
