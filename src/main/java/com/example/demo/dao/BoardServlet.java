package com.example.demo.dao;

import com.example.demo.dao.BoardDAO;
import com.example.demo.domain.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet( name = "BoardServlet" , urlPatterns = "/BoardServlet")
public class BoardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String title = request.getParameter("title");
            String id = request.getParameter("id");
            String pw = request.getParameter("pw");
            String context = request.getParameter("context");
            boolean isNotice = Boolean.parseBoolean(request.getParameter("isNotice"));

            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setTitle(title);
            boardDTO.setId(id);
            boardDTO.setPw(pw);
            boardDTO.setContext(context);
            boardDTO.setIsNotice(isNotice);
            boardDTO.setGroupLayer(Integer.valueOf("0"));
            boardDTO.setOriginNo(0);
            boardDTO.setGroupOrd(0);


        try {
                Class.forName("org.h2.Driver");
                Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

                BoardDAO boardDAO = new BoardDAO(conn);
                boardDAO.insertBoard(boardDTO);

                conn.close();

                response.sendRedirect("/feature");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("실패");
            }
        }

}

