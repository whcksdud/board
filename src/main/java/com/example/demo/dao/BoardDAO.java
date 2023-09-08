package com.example.demo.dao;

import com.example.demo.domain.BoardDTO;

import java.sql.*;

public class BoardDAO {
    private Connection conn;

    public BoardDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertBoard(BoardDTO boardDTO) throws SQLException {
        String insertQuery = "INSERT INTO BOARD (title, id, pw, context, writer_date, is_notice, ORIGIN_NO, GROUP_ORD, GROUP_LAYER) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, boardDTO.getTitle());
            pstmt.setString(2, boardDTO.getId());
            pstmt.setString(3, boardDTO.getPw());
            pstmt.setString(4, boardDTO.getContext());
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.setBoolean(6, boardDTO.isNotice()); // 추가된 isNotice 필드를 설정
            pstmt.setLong(7, boardDTO.getOriginNo()); // 부모 글 번호
            pstmt.setLong(8, boardDTO.getGroupOrd());
            pstmt.setLong(9, boardDTO.getGroupLayer()); // 깊이

            pstmt.executeUpdate();

            // 자동 증가한 NUM 값을 얻기
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                long generatedNum = generatedKeys.getLong(1); // 자동 증가한 NUM 값을 얻음

                // ORIGIN_NO를 NUM 값으로 업데이트
                String updateOriginNoQuery = "UPDATE BOARD SET ORIGIN_NO = ? WHERE NUM = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateOriginNoQuery)) {
                    updateStmt.setLong(1, generatedNum);
                    updateStmt.setLong(2, generatedNum);
                    updateStmt.executeUpdate();
                }
            } else {
                throw new SQLException("NUM 값을 얻을 수 없습니다.");
            }
        }
    }


}
