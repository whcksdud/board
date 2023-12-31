<%@ page import="java.util.*, java.sql.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.example.demo.dao.BoardDAO" %>
<%@ page import="com.example.demo.dao.CommentDAO" %>
<%@ page import="com.example.demo.domain.CommentDTO" %>
<%@ page import="com.example.demo.domain.BoardDTO" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
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

                        <div class="mt-5">
                                       <%
                                       String num = request.getParameter("num");


                                           BoardDAO boardDAO = new BoardDAO(); // BoardDAO 클래스의 인스턴스 생성
                                           BoardDTO board = boardDAO.getPostByNum(Integer.parseInt(num)); // getPostByNum 메서드 호출

                                           if (board != null) {
                                       %>
                        </div>
                        <div class="col-sm-10 offset-sm-2" align="right">
                                                                            <button type="button" class="btn btn-success ml-2 reply-button" data-bs-toggle="modal" data-bs-target="#replyModal">
                                                                                    <i class="bi bi-reply"></i> 답글
                                                                                </button>
                                                                            <!-- 수정 버튼 -->
                                                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#editModal">
                                                                                <i class="bi bi-pencil"></i> 수정
                                                                            </button>

                                                                            <!-- 수정 모달 -->
                                                                            <div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="editModalLabel" aria-hidden="true">
                                                                                <div class="modal-dialog">
                                                                                    <div class="modal-content">
                                                                                        <div class="modal-header">
                                                                                            <h5 class="modal-title" id="editModalLabel">게시글 수정</h5>
                                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                        </div>
                                                                                        <div class="modal-body">
                                                                                            <!-- 수정 내용 입력 폼 -->
                                                                                            <form id="editForm" method="post" action="EditPostServlet">
                                                                                                <input type="hidden" id="postNum" name="postNum" value="<%= num %>">
                                                                                                <div class="mb-3">
                                                                                                    <label for="editPassword" class="form-label">비밀번호</label>
                                                                                                    <input type="password" class="form-control" id="editPassword" name="editPassword" required>
                                                                                                </div>
                                                                                                <div class="mb-3">
                                                                                                    <label for="editTitle" class="form-label">제목</label>
                                                                                                    <input type="text" class="form-control" id="editTitle" name="editTitle" value="<%= board.getTitle() %>" required>
                                                                                                </div>
                                                                                                <div class="mb-3">
                                                                                                    <label for="editContext" class="form-label">내용</label>
                                                                                                    <textarea class="form-control" id="editContext" name="editContext" rows="5" required><%= board.getContext() %></textarea>
                                                                                                </div>
                                                                                                <button type="submit" class="btn btn-primary">수정 완료</button>
                                                                                            </form>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                            <!-- 답글 모달 -->
                                                                            <div class="modal fade" id="replyModal" tabindex="-1" aria-labelledby="replyModalLabel" aria-hidden="true">
                                                                                <div class="modal-dialog">
                                                                                    <div class="modal-content">
                                                                                        <div class="modal-header">
                                                                                            <h5 class="modal-title" id="replyModalLabel">답글 작성</h5>
                                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                        </div>
                                                                                        <div class="modal-body">
                                                                                            <!-- 답글 작성 폼 -->
                                                                                            <form id="replyForm" method="post" action="SaveReplyServlet">
                                                                                                <!-- 게시글 번호를 전달하기 위한 hidden input -->
                                                                                                <input type="hidden" id="postNum" name="postNum" value="<%= num %>">

                                                                                                <div class="mb-3">
                                                                                                    <label for="replyTitle" class="form-label">답글 제목</label>
                                                                                                    <input type="text" class="form-control" id="replyTitle" name="replyTitle" required>
                                                                                                </div>
                                                                                                <div class="mb-3">
                                                                                                    <label for="replyId" class="form-label">작성자 아이디</label>
                                                                                                    <input type="text" class="form-control" id="replyId" name="replyId" required>
                                                                                                </div>
                                                                                                <div class="mb-3">
                                                                                                    <label for="replyPw" class="form-label">비밀번호</label>
                                                                                                    <input type="password" class="form-control" id="replyPw" name="replyPw" required>
                                                                                                </div>
                                                                                                <div class="mb-3">
                                                                                                    <label for="replyContent" class="form-label">답글 내용</label>
                                                                                                    <textarea class="form-control" id="replyContent" name="replyContent" rows="5" required></textarea>
                                                                                                </div>
                                                                                                <button type="submit" class="btn btn-primary">답글 작성 완료</button>
                                                                                            </form>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>


                                                                            <!-- 삭제 버튼 -->
                                                                                <button type="button" class="btn btn-danger ml-2 delete-button" data-bs-toggle="modal" data-bs-target="#confirmDeleteModal">
                                                                                    <i class="bi bi-trash"></i> 삭제
                                                                                </button>
                                                                            <div class="modal fade" id="confirmDeleteModal" tabindex="-1" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
                                                                                <div class="modal-dialog">
                                                                                    <div class="modal-content">
                                                                                        <div class="modal-header">
                                                                                            <h5 class="modal-title" id="confirmDeleteModalLabel">비밀번호 확인</h5>
                                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                        </div>
                                                                                        <div class="modal-body">
                                                                                            <form id="confirmPasswordForm" method="post" action="DeletePostServlet">
                                                                                                <input type="hidden" id="postNum" name="postNum" value="<%= num %>">
                                                                                                <div class="mb-3">
                                                                                                    <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required>
                                                                                                </div>
                                                                                                <button type="submit" class="btn btn-danger">확인 및 삭제</button>
                                                                                            </form>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th colspan="4" style="text-align: center;"><%= board.getTitle() %></th>
                                </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <th style="text-align: center;">작성자</th>
                                    <td><%= board.getId() %></td>
                                    <th style="text-align: center;">작성일</th>
                                    <td><%= board.getWriterDate() %></td>
                                </tr>
                                <td colspan="4" style="padding: 30px;">
                                    <%= board.getContext() %>
                                </td>
                                <%
                                              } else {
                                                  out.println("게시글을 찾을 수 없습니다.");
                                              }

                                    %>
                            </tbody>

                        </table>
                                    <form id="commentForm" method="post" action="SaveCommentServlet">
                                        <input type="hidden" id="num" name="num" value="<%= request.getParameter("num") %>"> <!-- Hidden input to store the num value -->
                                        <div class="mb-3">

                                            <textarea class="form-control" id="comment" name="comment" rows="3" required></textarea>
                                        </div>
                                        <button type="submit" class="btn btn-primary">댓글 작성</button>
                                    </form>
                                    <div class="comment">
                                        <%

                                            CommentDAO commentDAO = new CommentDAO();
                                               Integer boardId = Integer.parseInt(num); // 문자열 num을 Integer로 변환
                                               List<CommentDTO> comments = commentDAO.getCommentsByBoardId(boardId);
                                            for (CommentDTO comment : comments) {
                                                Date writeDate = comment.getWriteDate(); // 댓글 작성일
                                                String content = comment.getCommentContent(); // 댓글 내용
                                                long comnum = comment.getNum();


                                        %>
                                        <div class="comment-header" style="padding-top: 10px;">
                                            <strong style="padding-right: 5px;">익명<%= comnum %></strong>
                                            <span><%= writeDate %></span>
                                        </div>
                                        <hr/>
                                        <div class="comment-content" style="padding: 10px;">
                                            <%= content %>
                                        </div>
                                        <%
                                            }

                                        %>
                                    </div>

                                    </div>
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