package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DBUtil {
    // 定义数据库配置文件路径
    private static final String PROPERTIES_FILE = "/db.properties";
    /**
     * 获取数据库连接
     *
     * @return Connection 数据库连接对象
     * @throws SQLException 如果无法建立连接
     */
    public static Connection getConnection() throws SQLException {
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  // 加载 MySQL 驱动
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            InputStream input = DBUtil.class.getResourceAsStream(PROPERTIES_FILE);
            if (input == null) {
                System.out.println("Unable to find db.properties file.");
            }
            Properties properties = new Properties();
            properties.load(input);

            // 读取配置文件中的数据库连接信息
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            Connection connection = DriverManager.getConnection(url, username, password);

            // 关闭自动提交事务
            connection.setAutoCommit(false);  // 禁用自动提交事务
            return connection;

        } catch (IOException e) {
            e.printStackTrace();
            throw new SQLException("Unable to load database properties file.", e);
        }
    }
}
