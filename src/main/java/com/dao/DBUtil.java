package com.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");



//    static{
//        try {
//            dataSource= new ComboPooledDataSource();//创建连接池实例
//
//            dataSource.setDriverClass("com.mysql.jdbc.Driver");//设置连接池连接数据库所需的驱动
//
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mydb?useSSL=false&serverTimezone=UTC&characterEncoding=utf-8&allowPublicKeyRetrieval=true");//设置连接数据库的URL
//
//            dataSource.setUser("root");//设置连接数据库的用户名
//
////            dataSource.setT
//
//            dataSource.setPassword("159630235z");//设置连接数据库的密码
//
//            dataSource.setMaxPoolSize(40);//设置连接池的最大连接数
//
//            dataSource.setMinPoolSize(2);//设置连接池的最小连接数
//
//            dataSource.setInitialPoolSize(10);//设置连接池的初始连接数
//
//            dataSource.setMaxStatements(100);//设置连接池的缓存Statement的最大数
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 使用连接池返回一个连接对象
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 返回连接池对象！
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }
}

