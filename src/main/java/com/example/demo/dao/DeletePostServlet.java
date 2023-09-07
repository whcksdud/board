package com.example.demo.dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet(name = "DeletePostServlet", urlPatterns = "/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 게시물 번호와 비밀번호를 폼에서 가져옵니다.
        long postNum = Long.parseLong(request.getParameter("postNum"));
        String password = request.getParameter("password");

        // JDBC 연결 정보
        String jdbcUrl = "jdbc:h2:~/test"; // H2 데이터베이스 URL
        String username = "sa"; // 사용자 이름
        String dbPassword = ""; // 데이터베이스 비밀번호

        try {
            // JDBC 드라이버를 로드하고 데이터베이스에 연결합니다.
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, username, dbPassword);

            // 비밀번호를 사용하여 게시물 삭제
            String deleteSQL = "DELETE FROM BOARD WHERE NUM = ? AND PW = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL);
            preparedStatement.setLong(1, postNum);
            preparedStatement.setString(2, password);

            int rowsAffected = preparedStatement.executeUpdate();

            // 삭제 결과에 따라 적절한 페이지로 리디렉션합니다.
            if (rowsAffected > 0) {
                response.sendRedirect("/feature");
            } else {
                response.getWriter().println("비밀번호가 일치하지 않습니다.");
            }

            conn.close();
        } catch (Exception e) {
            response.sendRedirect("/404");
        }
    }
}