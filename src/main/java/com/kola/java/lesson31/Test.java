package com.kola.java.lesson31;

import java.sql.*;

public class Test {
    public static void delete(String regName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "futurevistor";
            String pwd = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            connection = DriverManager.getConnection(url, userName, pwd);
            String sql = "delete from member where RegName=?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, "regName");
            statement.execute();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void select(String regName) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String userName = "futurevistor";
            String pwd = "123456";
            String url = "jdbc:mysql://120.78.128.25:3306/future";
            connection = DriverManager.getConnection(url, userName, pwd);
            String sql = "select * from member where RegName=?;";
            statement = connection.prepareStatement(sql);
            statement.setString(1, regName);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String id = set.getString(1);
                String regNames = set.getString(2);
                String pwds = set.getString(3);
                String MobilePhone = set.getString(4);
                System.out.println("id=" + id + ",regName=" + regNames + ",pwd=" + pwds + ",mobilephone=" + MobilePhone);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
//        delete("kola");
        select("kola-1");
    }


}
