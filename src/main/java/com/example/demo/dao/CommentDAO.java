package com.example.demo.dao;

import com.example.demo.domain.CommentDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

public class CommentDAO {
    private Connection connection;

    public CommentDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean insertCommentBoard(CommentDTO comment) {
        String query = "INSERT INTO Comment (BOARDID, WRITE_DATE, COMMENT_CONTENT) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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
}