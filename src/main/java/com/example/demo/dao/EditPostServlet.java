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

@WebServlet(name = "EditPostServlet", urlPatterns = "/EditPostServlet")

public class EditPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 비밀번호 확인 후 게시물 수정
        String editPassword = request.getParameter("editPassword");
        String editTitle = request.getParameter("editTitle");
        String editContext = request.getParameter("editContext");
        long postNum = Long.parseLong(request.getParameter("postNum"));

        // JDBC 연결 정보
        String jdbcUrl = "jdbc:h2:~/test"; // H2 데이터베이스 URL
        String username = "sa"; // 사용자 이름
        String dbPassword = ""; // 데이터베이스 비밀번호

        try {
            // JDBC 드라이버를 로드하고 데이터베이스에 연결합니다.
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, username, dbPassword);

            // 비밀번호를 사용하여 게시물 수정
            String updateSQL = "UPDATE BOARD SET TITLE = ?, CONTEXT = ? WHERE NUM = ? AND PW = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(updateSQL);
            preparedStatement.setString(1, editTitle);
            preparedStatement.setString(2, editContext);
            preparedStatement.setLong(3, postNum);
            preparedStatement.setString(4, editPassword);

            int rowsAffected = preparedStatement.executeUpdate();

            // 수정 결과에 따라 적절한 페이지로 리디렉션합니다.
            if (rowsAffected > 0) {
                response.sendRedirect("post?num=" + postNum); // Redirect back to the view page
            } else {
                // 비밀번호가 일치하지 않을 경우 처리 (예: 모달 창 띄우기)
                response.sendRedirect("/your-jsp-file-name.jsp?passwordMismatch=true");
            }

            conn.close();
        } catch (Exception e) {
            response.sendRedirect("/404"); // 오류 발생 시 이동할 페이지
        }


    }
}
