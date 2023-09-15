package com.example.demo.dao;

import com.example.demo.db.DatabaseConnection;
import com.example.demo.domain.BoardDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {
    private Connection conn;

    //답글 메소드
    public void insertBoard(BoardDTO boardDTO) throws SQLException {
        conn = DatabaseConnection.getConnection();
        String insertQuery = "INSERT INTO BOARD (title, id, pw, context, writer_date, is_notice, ORIGIN_NO, GROUP_ORD, GROUP_LAYER) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, boardDTO.getTitle());
            pstmt.setString(2, boardDTO.getId());
            pstmt.setString(3, boardDTO.getPw());
            pstmt.setString(4, boardDTO.getContext());
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.setBoolean(6, boardDTO.getisNotice()); // 추가된 isNotice 필드를 설정
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
        conn.close();
    }
    public List<BoardDTO> getAllPosts() throws SQLException {
        conn = DatabaseConnection.getConnection();
        List<BoardDTO> posts = new ArrayList<>();
        String query = "SELECT * FROM BOARD ORDER BY is_notice DESC, ORIGIN_NO ASC, GROUP_ORD ASC";

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                BoardDTO post = new BoardDTO();
                post.setNum(rs.getLong("num"));
                post.setTitle(rs.getString("title"));
                post.setId(rs.getString("id"));
                post.setWriterDate(rs.getDate("writer_date"));
                post.setNotice(rs.getBoolean("is_notice"));
                post.setGroupLayer(rs.getInt("group_Layer"));
                // 필요한 다른 필드 설정
                posts.add(post);
            }
        }
        conn.close();
        return posts;
    }

    // 게시글을 검색하는 메서드
    public List<BoardDTO> searchPosts(String searchKeyword) throws SQLException {
        conn = DatabaseConnection.getConnection();
        List<BoardDTO> posts = new ArrayList<>();
        String query = "SELECT * FROM BOARD WHERE title LIKE ?";
        searchKeyword = "%" + searchKeyword + "%";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, searchKeyword);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardDTO post = new BoardDTO();
                post.setNum(rs.getLong("num"));
                post.setTitle(rs.getString("title"));
                post.setId(rs.getString("id"));
                post.setWriterDate(rs.getDate("writer_date"));

                // 필요한 다른 필드 설정

                posts.add(post);
            }
        }
        conn.close();
        return posts;
    }
    public BoardDTO getPostByNum(int num) throws SQLException {
        conn = DatabaseConnection.getConnection();
        BoardDTO board = new BoardDTO();
        String query = "SELECT * FROM BOARD WHERE num = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, num);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                board.setNum(rs.getInt("num"));
                board.setTitle(rs.getString("title"));
                board.setId(rs.getString("id"));
                board.setWriterDate(rs.getTimestamp("writer_date"));
                board.setContext(rs.getString("context"));
                board.setGroupLayer(rs.getInt("group_layer"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
        return board;
    }

}
