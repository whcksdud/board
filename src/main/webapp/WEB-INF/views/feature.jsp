<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="com.example.demo.dao.BoardDAO" %>
<%@ page import="com.example.demo.domain.BoardDTO" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<%
  BoardDAO boardDAO = new BoardDAO();
  List<BoardDTO> posts = boardDAO.getAllPosts();
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>스프링부트 게시판 플젝입니다</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Inter:wght@700;800&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>

    <div class="container-xxl bg-white p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Navbar & Hero Start -->
        <div class="container-xxl position-relative p-0">
            <nav class="navbar navbar-expand-lg navbar-light px-4 px-lg-5 py-3 py-lg-0">
                <a href="/home" class="navbar-brand p-0">
                    <h1 class="m-0">게시판 플젝</h1>
                    <!-- <img src="img/logo.png" alt="Logo"> -->
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="fa fa-bars"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto py-0">
                        <a href="/home" class="nav-item nav-link">홈</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle active" data-bs-toggle="dropdown">페이지</a>
                            <div class="dropdown-menu m-0">
                                <a href="feature" class="dropdown-item active">게시판</a>
                                <a href="votefeature" class="dropdown-item">투표</a>
                                                                <a href="game" class="dropdown-item">게임</a>

                            </div>
                        </div>
                    </div>
                </div>
            </nav>

            <div class="container-xxl bg-primary page-header">
                <div class="container text-center">
                    <h1 class="text-white animated zoomIn mb-3">게시판</h1>
                </div>
            </div>

        </div>
        <!-- Navbar & Hero End -->

        <!-- Features Start -->
        <div class="container-xxl py-6">
            <div class="container">
                    <!-- Sample Posts -->
                  <div class="container mt-5">
                      <div class="container">
                          <%
                            String searchKeyword = request.getParameter("searchKeyword");
                              List<BoardDTO> searchPosts = boardDAO.searchPosts(searchKeyword);
                            if (searchKeyword != null) {
                                        for (BoardDTO searchPost : searchPosts){
                                                      String snum = String.valueOf(searchPost.getNum());
                                                      String stitle = searchPost.getTitle();
                                                      String sid = searchPost.getId();
                                                      String swriterDate = searchPost.getWriterDate().toString();
                          %>
                          <h4 class="mt-4">검색 결과</h4>
                          <table class="table">
                              <thead>
                                  <tr>
                                      <th scope="col">번호</th>
                                      <th scope="col">제목</th>
                                      <th scope="col">작성자</th>
                                      <th scope="col">작성일</th>
                                  </tr>
                              </thead>
                              <tbody>
                                    <tr>
                                      <th scope="row"><%= snum %></th>
                                      <td><a href="/post?num=<%= snum %>"><%= stitle %></a></td>
                                      <td><%= sid %></td>
                                      <td><%= swriterDate %></td>
                                  </tr>
                              </tbody>
                          </table>
                          <%}}%>
                          <h4 class="mt-4">전체 게시글</h4>


                          <table class="table">
                              <thead>
                                  <tr>
                                      <th scope="col">번호</th>
                                      <th scope="col">제목</th>
                                      <th scope="col">작성자</th>
                                      <th scope="col">작성일</th>
                                  </tr>
                              </thead>
                              <tbody>

                                  <%
                                  for (BoardDTO post : posts)  {
                                  String num = String.valueOf(post.getNum());
                                                      String title = post.getTitle();
                                                      String id = post.getId();
                                                      String writerDate = post.getWriterDate().toString();
                                                      boolean isNotice = post.getisNotice();
                                                      int groupLayer = post.getGroupLayer();

                                      if (isNotice) { %>
                                          <tr>
                                              <th scope="row"><span class="text-danger">공지</span></th>
                                              <td><a href="/post?num=<%= num %>"><%= title %></a></td>
                                              <td><%= id %></td>
                                              <td><%= writerDate %></td>
                                          </tr>
                                      <% } else {
                                          if (groupLayer >= 2) { %>
                                              <tr>
                                                  <th scope="row"><%= num %></th>
                                                  <td><a href="/post?num=<%= num %>">&nbsp;&nbsp; Re)<%= title %></a></td>
                                                  <td><%= id %></td>
                                                  <td><%= writerDate %></td>
                                              </tr>
                                          <% } else { %>
                                              <tr>
                                                  <th scope="row"><%= num %></th>
                                                  <td><a href="/post?num=<%= num %>"><%= title %></a></td>
                                                  <td><%= id %></td>
                                                  <td><%= writerDate %></td>
                                              </tr>
                                          <% }
                                      }
                                  }

                                  %>
                              </tbody>
                          </table>



                          <div class="text-center">
                              <button type="button" class="btn btn-primary" onClick="location.href='add'">글 작성</button>
                          </div>
                      </div>
                      <br>
                      <br>
                      <br>
                      <br>
                      <form method="GET" action="feature">
                                               <div class="input-group mb-1 d-flex justify-content-center">
                                                   <div class="col-md-6">
                                                       <input type="text" class="form-control" placeholder="검색어를 입력하세요" name="searchKeyword">
                                                   </div>
                                                   <div class="col-md-0">
                                                       <button class="btn btn-outline-secondary btn-block" type="submit">검색</button>
                                                   </div>
                                               </div>

                                            </form>
                  </div>

            </div>
        </div>
        <!-- Features End -->


        <!-- Footer Start -->
        <div class="container-fluid bg-dark text-light footer pt-5 wow fadeIn" data-wow-delay="0.1s" style="margin-top: 6rem;">
            <div class="container py-2">
                <p><i class="bi bi-shield-lock-fill"></i> copyright chanyougjo</p>
            </div>

        </div>
        </div>
        <!-- Footer End -->


        <!-- Back to Top -->
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
    </div>

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>