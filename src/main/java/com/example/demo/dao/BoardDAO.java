package com.example.demo.dao;

import com.example.demo.domain.BoardDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Connection;

public class BoardDAO {
    private Connection conn;

    public BoardDAO(Connection conn) {
        this.conn = conn;
    }

    public void insertBoard(BoardDTO boardDTO) throws SQLException {
        String insertQuery = "INSERT INTO BOARD (title, id, pw, context, writer_date) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setString(1, boardDTO.getTitle());
            pstmt.setString(2, boardDTO.getId());
            pstmt.setString(3, boardDTO.getPw());
            pstmt.setString(4, boardDTO.getContext());
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.executeUpdate();
        }
    }
}
