package com.kola.java.lesson33;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JDBC_V5 {
    private static String userName = "futurevistor";
    private static String pwd = "123456";
    private static String url = "jdbc:mysql://120.78.128.25:3306/future";

    /* 静态代码块：
     通过静态代码块将注册驱动，以及获取连接信息的操作只运行一遍
     */
    static {
        // 注册驱动（装载jvm）
        try {
            String driverPath = "com.mysql.jdbc.Driver";
            Class.forName(driverPath);
            Properties properties = new Properties();
            properties.load(JDBC_V5.class.getResourceAsStream("/jdbc.properties"));
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

    // 连接数据库
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, pwd);
    }

    // 适用于增删改
    public static void execute(String sql, Object... params) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 连接数据库
            connection = getConnection();
            // 准备陈述对象
            statement = connection.prepareStatement(sql);
            // 设置占位符参数
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            // 执行sql语句
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接，释放资源
            close(connection, statement);
        }
    }

    private static void close(Connection connection, PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // 释放资源
    private static void close(PreparedStatement statement, Connection connection, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // 关闭连接，释放资源
        if (statement != null) {
            try {
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    1，查询的sql结果不一样
    2，查询的参数不一样
    3，结果集的记录数，字段数不确定--Hashmap
    4，List<Map<String,String>>
     */

    // 查
    public static List<Map<String, String>> select(String sql, Object... params) {
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        List<Map<String, String>> allDatas = new ArrayList<>();
        try {
            connection = getConnection();
            // 准备陈述对象
            statement = connection.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            // 执行sql语句
            resultSet = statement.executeQuery();
            // 获得结果集的元数据
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获得列数
            int columnCount = metaData.getColumnCount();
            // 遍历每一条记录
            while (resultSet.next()) {
                // 创建容器保存每行数据
                Map<String, String> rowData = new HashMap<>();
                // 解析每条记录的值
                for (int i = 1; i <= columnCount; i++) {
                    // 获得每个字段的key，value，并放入map容器中
                    String columnValue = resultSet.getString(i);
                    String columnName = metaData.getColumnName(i);
                    rowData.put(columnName, columnValue);
                }
                allDatas.add(rowData);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement, connection, resultSet);
        }
        return allDatas;
    }


    public static void main(String[] args) {
//        String sql = "update member set leaveamount=?;";
//        execute(sql, 300);
        String sql = "select *from member where regname=?;";
        List<Map<String, String>> datas = select(sql, "cc");
        for (Map<String, String> data : datas) {
            System.out.println(data.toString());
        }
    }
}
