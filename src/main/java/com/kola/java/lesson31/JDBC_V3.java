package com.kola.java.lesson31;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBC_V3 {
    private static String userName = "futurevistor";
    private static String pwd = "123456";
    private static String url = "jdbc:mysql://120.78.128.25:3306/future";

    /* 静态代码块：
     通过静态代码块将注册驱动，以及获取连接信息的操作只运行一遍
     */
    static {
        // 注册驱动（装载jvm）
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.load(JDBC_V3.class.getResourceAsStream("/jdbc.properties"));
            properties.getProperty("jdbc.drvier");
            properties.getProperty("jdbc.userName");
            properties.getProperty("jdbc.pwd");
            properties.getProperty("jdbc.url");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insert(String regName, String pwds, String mobilePhone) {
        try {
            // 连接数据库
            Connection connection = getConnection();
            String sql = "insert into member(regName,pwd,mobilephone) values(?,?,?)";
            // 准备陈述对象
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, regName);
            statement.setString(2, mobilePhone);
            statement.setString(3, mobilePhone);
            // 执行sql语句
            statement.execute();
            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // 连接数据库
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, pwd);
    }

    public static void delete(String regName) {
        try {
            // 连接数据库
            Connection connection = getConnection();
            String sql = "delete from member where regName=?";
            // 准备陈述对象
            PreparedStatement statement = connection.prepareStatement(sql);
            // 执行sql语句
            statement.execute();
            statement.setString(1, "regName");
            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update(String regName) {
        try {
            Connection connection = getConnection();
            String sql = "update member set pwd ='123456'  where regName=?;";
            // 准备陈述对象
            PreparedStatement statement = connection.prepareStatement(sql);
            // 执行sql语句
            statement.execute();
            statement.setString(1, "regName");
            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void select() {
        try {
            Connection connection = getConnection();
            String sql = "select *from member;";
            // 准备陈述对象
            PreparedStatement statement = connection.prepareStatement(sql);
            // 执行sql语句
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String regName = resultSet.getString(2);
                String Pwd = resultSet.getString(3);
                String mobilePhone = resultSet.getString(4);
                System.out.println("id=" + id + ",regName=" + regName + ",Pwd=" + pwd + ",mobilePhone=" + mobilePhone);
            }

            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        select();
    }
}
