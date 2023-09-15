package com.example.demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conn;

    // JDBC URL, 사용할 데이터베이스 URL을 입력합니다.
    private static final String DB_URL = "jdbc:h2:~/test"; // 여기서는 H2 데이터베이스를 사용합니다.
    private static final String DB_USER = "sa"; // 데이터베이스 사용자 이름
    private static final String DB_PASSWORD = ""; // 데이터베이스 비밀번호

    public static Connection getConnection() throws SQLException {
        try {
            if (conn == null || conn.isClosed()) {
                Class.forName("org.h2.Driver"); // H2 데이터베이스 드라이버를 로드합니다.
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
            return conn;
        } catch (ClassNotFoundException e) {
            throw new SQLException("데이터베이스 드라이버 로드 실패: " + e.getMessage());
        }
    }

    public static void closeConnection() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
