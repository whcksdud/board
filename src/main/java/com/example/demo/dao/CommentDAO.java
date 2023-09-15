package com.example.demo.dao;

import com.example.demo.db.DatabaseConnection;
import com.example.demo.domain.CommentDTO;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private Connection con;

    public boolean insertCommentBoard(CommentDTO comment) throws SQLException {
        con = DatabaseConnection.getConnection();
        String query = "INSERT INTO Comment (BOARDID, WRITE_DATE, COMMENT_CONTENT) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setLong(1, comment.getBoardId());
            preparedStatement.setTimestamp(2, new Timestamp(Instant.now().getEpochSecond() * 1000));
            preparedStatement.setString(3, comment.getCommentContent());


            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        }
    }
    public List<CommentDTO> getCommentsByBoardId(Integer boardId) throws SQLException {
        List<CommentDTO> comments = new ArrayList<>();
        String query = "SELECT * FROM COMMENT WHERE boardid = ?";
        con = DatabaseConnection.getConnection();

        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setInt(1, boardId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CommentDTO comment = new CommentDTO();
                comment.setNum((long) rs.getInt("NUM"));
                comment.setBoardId((long) rs.getInt("BOARDID"));
                comment.setWriteDate(rs.getTimestamp("WRITE_DATE"));
                comment.setCommentContent(rs.getString("COMMENT_CONTENT"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        con.close();
        return comments;
    }
}