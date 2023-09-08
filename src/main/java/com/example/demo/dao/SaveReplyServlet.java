package com.example.demo.dao;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.*;
import java.util.Date;

@WebServlet(name = "SaveReplyServlet", urlPatterns = "/SaveReplyServlet")
public class SaveReplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // 데이터베이스 연결 정보
        String jdbcUrl = "jdbc:h2:~/test"; // H2DB 연결 URL
        String dbUser = "sa"; // H2DB 사용자 이름
        String dbPassword = ""; // H2DB 비밀번호

        try {
            // JDBC 드라이버 로드
            Class.forName("org.h2.Driver");

            // 데이터베이스 연결
            Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // 답글 제목, 작성자 아이디, 비밀번호, 답글 내용을 파라미터에서 가져옵니다.
            String replyTitle = request.getParameter("replyTitle");
            String replyId = request.getParameter("replyId");
            String replyPw = request.getParameter("replyPw");
            String replyContent = request.getParameter("replyContent");

            // 원글의 num 값을 얻어온다 (여기에서는 request.getParameter() 등을 사용)
            Integer originalNum = Integer.parseInt(request.getParameter("postNum"));
            Integer Num = getOriginNoByNum(originalNum, connection);
            //updateOriginNo(originalNum, connection);
            // 그룹내 순서(groupOrd)를 구하기 위해 해당 그룹의 마지막 답글의 groupOrd 값을 얻어온다
            Integer lastGroupOrd = getLastGroupOrd(originalNum, connection);

            // 계층(groupLayer) 설정 (원글 + 1)
            Integer groupLayer = originalNum + 1;

            // SQL 쿼리 준비
            String sql = "INSERT INTO BOARD (ORIGIN_NO, GROUP_ORD, GROUP_LAYER, TITLE, id, pw, context, writer_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // 쿼리 매개변수 설정
            preparedStatement.setInt(1, Num); // 원글의 num값을 그룹(originNo)으로 설정
            preparedStatement.setInt(2, lastGroupOrd + 1); // 그룹내 순서(groupOrd)
            preparedStatement.setInt(3, groupLayer); // 계층(groupLayer)
            preparedStatement.setString(4, replyTitle);
            preparedStatement.setString(5, replyId);
            preparedStatement.setString(6, replyPw);
            preparedStatement.setString(7, replyContent);
            preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));

            preparedStatement.executeUpdate();
            connection.close();
            response.sendRedirect("/feature");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // 에러 페이지로 이동
        }
    }
    // getLastGroupOrd 함수 구현 예제
    private int getLastGroupOrd(int originNum, Connection connection) throws SQLException {
        int lastGroupOrd = 0;

        try {
            String sql = "SELECT MAX(GROUP_ORD) FROM BOARD WHERE ORIGIN_NO = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, originNum);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                lastGroupOrd = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            // 예외 처리
            e.printStackTrace();
        }

        return lastGroupOrd;
    }
    // 원글의 num 값을 사용하여 ORIGIN_NO를 업데이트하는 메서드
    private void updateOriginNo(int originalNum, Connection connection) throws SQLException {
        try {
            // SQL 쿼리 준비
            String updateSql = "UPDATE BOARD SET ORIGIN_NO = ? WHERE num = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateSql);

            // 쿼리 매개변수 설정
            updateStatement.setInt(1, originalNum); // 업데이트할 ORIGIN_NO 값으로 originalNum을 설정
            updateStatement.setInt(2, originalNum); // WHERE 조건으로 원글의 num 값을 사용

            // 업데이트 실행
            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                // 업데이트 성공
            } else {
                // 원글의 num과 일치하는 데이터가 없을 때의 처리
            }
        } catch (SQLException e) {
            // 예외 처리
            e.printStackTrace();
        }
    }
    // 원글의 NUM 값을 사용하여 ORIGIN_NO를 조회하는 메서드
    private Integer getOriginNoByNum(int num, Connection connection) throws SQLException {
        Integer originNo = null;

        try {
            String sql = "SELECT ORIGIN_NO FROM BOARD WHERE NUM = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, num);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                originNo = resultSet.getInt("ORIGIN_NO");
            }
        } catch (SQLException e) {
            // 예외 처리
            e.printStackTrace();
        }

        return originNo;
    }


}
