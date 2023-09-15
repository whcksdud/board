package com.example.demo.dao;

import com.example.demo.domain.BoardDTO;
import com.example.demo.domain.CommentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

@WebServlet(name = "SaveCommentServlet", urlPatterns = "/SaveCommentServlet")
public class SaveCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String commentContent = request.getParameter("comment");
        Long numStr = Long.valueOf(request.getParameter("num")); // 게시물 고유 번호


        if (commentContent != null) {
            try {
                // Create a CommentDTO instance
                CommentDTO comment = new CommentDTO();
                comment.setCommentContent(commentContent);
                comment.setBoardId(numStr);


                CommentDAO commentDAO = new CommentDAO();

                // Insert the comment
                boolean success = commentDAO.insertCommentBoard(comment);



                if (success) {
                    response.sendRedirect("post?num=" + numStr); // Redirect back to the view page
                } else {
                    response.getWriter().println("댓글 등록 중 오류가 발생했습니다.");
                }

            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("댓글 등록 중 오류가 발생했습니다: " + e.getMessage());
            }
        } else {
            response.getWriter().println("111111111111111111111111111111111111111111111111111");
        }
    }
}
