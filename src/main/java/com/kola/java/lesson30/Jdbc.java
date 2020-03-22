package com.kola.java.lesson30;

import java.sql.*;

/*
jdbc:
1，java database connectivity(java 数据库连接）
2，用于执行sql语句的api，java语言编写的类和接口
3，为多种关系型数据库提供统一访问
 */
public class Jdbc {
    public static void insert() {
        try {
            // 注册驱动（装载jvm）
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            String userName = "futurevistor";
            String pwd = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            Connection connection = DriverManager.getConnection(url, userName, pwd);
            String sql = "insert into member(regName,pwd,mobilephone) values('kola','123456','11111111111')";
            // 准备陈述对象
            Statement statement = connection.createStatement();
            // 执行sql语句
            statement.execute(sql);
            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void delete() {
        try {
            // 注册驱动（装载jvm）
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            String userName = "futurevistor";
            String pwd = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            Connection connection = DriverManager.getConnection(url, userName, pwd);
            String sql = "delete from member where regName='kola'";
            // 准备陈述对象
            Statement statement = connection.createStatement();
            // 执行sql语句
            statement.execute(sql);
            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void update() {
        try {
            // 注册驱动（装载jvm）
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            String userName = "futurevistor";
            String pwd = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            Connection connection = DriverManager.getConnection(url, userName, pwd);
            String sql = "update member set pwd ='123456'  where regName='kola';";
            // 准备陈述对象
            Statement statement = connection.createStatement();
            // 执行sql语句
            statement.execute(sql);
            // 关闭连接，释放资源
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void select() {
        try {
            // 注册驱动（装载jvm）
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            String userName = "futurevistor";
            String pwd = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            Connection connection = DriverManager.getConnection(url, userName, pwd);
            String sql = "select *from member;";
            // 准备陈述对象
            Statement statement = connection.createStatement();
            // 执行sql语句
            ResultSet resultSet = statement.executeQuery(sql);
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
//        insert();
        select();
    }
}
