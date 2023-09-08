<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.io.*" %>
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
                </a>
            </nav>
            <div class="container-xxl bg-primary page-header">
            </div>
        </div>
        <!-- Navbar & Hero End -->
        <!-- Quote Start -->
        <div class="container-xxl py-6">
            <div class="container">
                <div class="mx-auto text-center wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                    <h2 class="mb-5">게시글 작성</h2>
                </div>
                <div class="row justify-content-center">
                    <div class="col-lg-7 wow fadeInUp" data-wow-delay="0.3s">

                        <form method="post" action="BoardServlet" class="needs-validation" novalidate>
                            <div class="form-group row">
                                <label class="col-sm-2 col-form-label">공지 여부 </label>
                                <br>
                                <br>

                                <div class="col-sm-10">
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="isNotice" id="yesNotice" value="true" required>
                                        <label class="form-check-label" for="yesNotice">예</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="isNotice" id="noNotice" value="false" required>
                                        <label class="form-check-label" for="noNotice">아니오</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group row">
                                <label for="title" class="col-sm-2 col-form-label">제목 </label>
                                <div class="col-sm-10">
                                    <input type="text" name="title" class="form-control" required>
                                </div>
                            </div>
                            <br>
                            <div class="form-group row">
                                <label for="name" class="col-sm-2 col-form-label">아이디 </label>
                                <div class="col-sm-10">
                                    <input type="text" name="id" class="form-control" required>
                                </div>
                            </div>
                            <br>
                            <div class="form-group row">
                                <label for="pass" class="col-sm-2 col-form-label">비밀번호 </label>
                                <div class="col-sm-10">
                                    <input type="password" name="pw" class="form-control" required>
                                </div>
                            </div>
                            <br>
                            <div class="form-group row">
                                <label for="content" class="col-sm-2 col-form-label">내용</label>
                                <div class="col-sm-10">
                                    <input cols="100" rows="5" name="context" class="form-control" required></input>
                                </div>


                            </div>
                            <br>
                               <div class="form-group row">
                                                                            <div class="col-sm-10 offset-sm-2">
                                                                                <!-- 투표 버튼 -->


                                                                                <!-- 사진 아이콘 버튼 -->
                                                                                <button class="btn btn-outline-primary ml-2">
                                                                                    <i class="bi bi-camera"></i> 사진 업로드
                                                                                </button>

                                                                            </div>
                                                                        </div>
                            <br>

                            <div class="form-group row">
                                <div class="col-sm-10 offset-sm-2">
                                    <button type="submit" class="btn btn-primary">등록</button>
                                    
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Quote End -->
        

 


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