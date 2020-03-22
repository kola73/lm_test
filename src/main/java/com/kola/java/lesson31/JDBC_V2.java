package com.kola.java.lesson31;

import java.sql.*;

public class JDBC_V2 {
    public static void insert(String regName, String pwd, String mobilephone) {
        try {
            // 注册驱动（装载jvm）
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            String userName = "futurevistor";
            String pwd2 = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            Connection connection = DriverManager.getConnection(url, userName, pwd2);
            // 用占位符
            String sql = "insert into member(regName,pwd,mobilephone) values(?,?,?)";
            // 准备陈述对象（用preparedStatement可以防止sql注入）
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, regName);
            statement.setString(2, pwd);
            statement.setString(3, mobilephone);
            // 执行sql语句
            statement.execute();
            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void delete(String regName) {
        try {
            // 注册驱动（装载jvm）
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            String userName = "futurevistor";
            String pwd = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            Connection connection = DriverManager.getConnection(url, userName, pwd);
            String sql = "delete from member where regName=?";
            // 准备陈述对象（用prepareStatement可以防止sql注入）
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, regName);
            // 执行sql语句
            statement.execute();
            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update(String regName) {
        try {
            // 注册驱动（装载jvm）
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            String userName = "futurevistor";
            String pwd = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            Connection connection = DriverManager.getConnection(url, userName, pwd);
            String sql = "update member set pwd ='123456aa'  where regName=?;";
            // 准备陈述对象
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, regName);
            // 执行sql语句
            statement.execute();
            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void select(String mobilephone, String pwd) {
        try {
            // 注册驱动（装载jvm）
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            String userName = "futurevistor";
            String pwd2 = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            Connection connection = DriverManager.getConnection(url, userName, pwd2);
            String sql = "select *from member where mobilephone=? and pwd=?;";
            // 准备陈述对象
            PreparedStatement statement = connection.prepareStatement(sql);
            // 为占位符设值
            statement.setString(1, mobilephone);
            statement.setString(2, pwd);
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            insert("kola-"+i, "123456a", "13211111111"+i);
        }
//        select("13125068926", "E10ADC3949BA59ABBE56E057F20F883E");
//        insert("kola","123456a","13211111111");
//        delete("cc");
        update("小蜜蜂");
    }

}
