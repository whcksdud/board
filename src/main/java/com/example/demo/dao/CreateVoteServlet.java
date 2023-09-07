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
import java.sql.SQLException;


@WebServlet(name = "CreateVoteServlet", urlPatterns = "/CreateVoteServlet")

public class CreateVoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // H2 데이터베이스 JDBC 연결 정보
    private static final String DB_URL = "jdbc:h2:~/test"; // 데이터베이스 URL (본인의 설정에 따라 수정)
    private static final String DB_USER = "sa"; // 데이터베이스 사용자 (기본값은 "sa")
    private static final String DB_PASSWORD = ""; // 데이터베이스 비밀번호 (기본값은 빈 문자열)

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 폼에서 입력된 투표 제목을 가져옵니다.
        String voteTitle = request.getParameter("voteTitle");

        // 폼에서 입력된 투표 옵션을 배열로 가져옵니다.
        String[] voteOptions = new String[5]; // 최대 5개의 옵션을 입력받도록 설정 (조정 가능)
        for (int i = 1; i <= 5; i++) {
            String option = request.getParameter("option" + i);
            if (option != null && !option.isEmpty()) {
                voteOptions[i - 1] = option;
            }
        }

        // H2 데이터베이스 연결
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 투표 정보를 저장하기 위한 SQL 쿼리
            String insertVoteQuery = "INSERT INTO Vote (title) VALUES (?)";

            // 투표 옵션을 저장하기 위한 SQL 쿼리
            String insertOptionQuery = "INSERT INTO Vote_Option (vote_id, option_text) VALUES (?, ?)";

            // 투표 정보를 데이터베이스에 저장
            try (PreparedStatement voteStmt = conn.prepareStatement(insertVoteQuery, new String[] { "id" })) {
                voteStmt.setString(1, voteTitle);
                voteStmt.executeUpdate();

                // 생성된 투표의 ID 가져오기
                int voteId;
                try (var generatedKeys = voteStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        voteId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("투표 ID를 가져오지 못했습니다.");
                    }
                }

                // 투표 옵션을 데이터베이스에 저장
                try (PreparedStatement optionStmt = conn.prepareStatement(insertOptionQuery)) {
                    for (String option : voteOptions) {
                        if (option != null) {
                            optionStmt.setInt(1, voteId);
                            optionStmt.setString(2, option);
                            optionStmt.executeUpdate();
                        }
                    }
                }
            }

            // 투표 생성 후, 생성된 투표 페이지로 리다이렉션
            response.sendRedirect("/votefeature"); // 투표 목록 페이지로 리다이렉션할 URL을 지정하세요.
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("/404"); // 오류 페이지로 리다이렉션
        }
    }
}
