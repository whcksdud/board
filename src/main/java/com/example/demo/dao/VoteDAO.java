package com.example.demo.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.db.DatabaseConnection;
import com.example.demo.domain.VoteDTO;

public class VoteDAO {
    private Connection conn;

    public List<VoteDTO> getVoteList() throws SQLException {
        List<VoteDTO> voteList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            String query = "SELECT * FROM VOTE";
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();

            while (rs.next()) {
                VoteDTO vote = new VoteDTO();
                vote.setId(rs.getLong("id"));
                vote.setTitle(rs.getString("title"));
                voteList.add(vote);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return voteList;
    }
    public List<String> getVoteOptions(String voteId) throws SQLException {
        List<String> options = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = DatabaseConnection.getConnection();

        try {
            String query = "SELECT OPTION_TEXT FROM VOTE_OPTION WHERE VOTE_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, voteId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                options.add(rs.getString("OPTION_TEXT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return options;
    }

    public String getVoteTitle(String voteId) throws SQLException {
        String title = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        conn = DatabaseConnection.getConnection();
        try {
            String query = "SELECT TITLE FROM VOTE WHERE ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, voteId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                title = rs.getString("TITLE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return title;
    }
}
