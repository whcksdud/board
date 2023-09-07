<%@ page import="java.util.*, java.sql.*" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>스프링부트 게시판 플젝입니다</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

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
                                <a href="feature" class="dropdown-item">게시판</a>
                                <a href="votefeature" class="dropdown-item active">투표</a>
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
            <div class="container text-center"> <!-- text-center 추가 -->
                <!-- Sample Posts -->
                 <div class="container mt-5">
                            <div class="mt-5">
                                <%
                                    String num = request.getParameter("ID");
                                    ArrayList<String> optionTexts = new ArrayList<>();

                                    try {
                                        Class.forName("org.h2.Driver");
                                        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

                                        Statement stmt = conn.createStatement();
                                        String query = "SELECT * FROM VOTE WHERE ID = " + num;
                                        ResultSet rs = stmt.executeQuery(query);

                                        if (rs.next()) {
                                %>
                                <div class="col-sm-10 offset-sm-2 mx-auto">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th colspan="4" style="text-align: center;"><%= rs.getString("title") %></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                Statement optionStmt = conn.createStatement();
                                                String optionQuery = "SELECT * FROM VOTE_OPTION WHERE vote_id = " + num;
                                                ResultSet optionRS = optionStmt.executeQuery(optionQuery);

                                                while (optionRS.next()) {
                                                    String optionText = optionRS.getString("OPTION_TEXT");
                                                    optionTexts.add(optionText);
                                                    int optionId = optionRS.getInt("id");
                                            %>
                                            <tr>
                                                <td>
                                                    <input class="form-check-input" type="radio" name="option" id="option<%= optionId %>" value="<%= optionId %>">
                                                    <label class="form-check-label" for="option<%= optionId %>">
                                                        <%= optionText %>
                                                    </label>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                                optionRS.close();
                                                optionStmt.close();
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <%
                                        }
                                        rs.close();
                                        stmt.close();
                                        conn.close();

                                    } catch (Exception e) {
                                        out.println("데이터베이스 조회 도중 오류가 발생하였습니다: " + e.getMessage());
                                    }
                                %>

                                <!-- 차트 표시 영역 추가 -->
                                <div class="col-sm-10 offset-sm-2 mx-auto">
                                    <canvas id="voteChart" width="400" height="200"></canvas>
                                </div>

                                <!-- 추가된 코드: 투표 버튼 -->
                                <button id="voteButton" class="btn btn-primary">투표하기</button>
                                <div id="voteResult" class="mt-3"></div>

                                <script>
                                    var optionData = <%= new org.json.JSONArray(optionTexts) %>;
                                    var ctx = document.getElementById('voteChart').getContext('2d');
                                    var chart = new Chart(ctx, {
                                        type: 'bar',
                                        data: {
                                            labels: optionData,
                                            datasets: [{
                                                label: '투표 옵션',
                                                data: new Array(optionData.length).fill(0),
                                                backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                                borderColor: 'rgba(75, 192, 192, 1)',
                                                borderWidth: 1
                                            }]
                                        },
                                        options: {
                                            scales: {
                                                y: {
                                                    beginAtZero: true,
                                                    stepSize: 1, // 이 부분을 1로 설정하여 1의 단위로 스케일링
                                                }
                                            }
                                        }
                                    });


                                    document.getElementById("voteButton").addEventListener("click", function () {
                                        var selectedOption = document.querySelector('input[name="option"]:checked');

                                        if (selectedOption) {
                                            var optionId = selectedOption.value;
                                            var num = '<%= num %>';

                                            var xhr = new XMLHttpRequest();
                                            xhr.open("POST", "/vote", true);
                                            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                            xhr.onreadystatechange = function () {
                                                if (xhr.readyState === 4 && xhr.status === 200) {
                                                    // 투표 결과 출력
                                                    document.getElementById("voteResult").innerHTML = "투표 결과: " + xhr.responseText;

                                                    // 옵션 득표 수 가져오기
                                                    var voteCountsXHR = new XMLHttpRequest();
                                                    voteCountsXHR.open("GET", "/getVoteCounts?num=" + num, true);
                                                    voteCountsXHR.onreadystatechange = function () {
                                                        if (voteCountsXHR.readyState === 4 && voteCountsXHR.status === 200) {
                                                            var voteCounts = JSON.parse(voteCountsXHR.responseText);

                                                            // 차트 데이터 업데이트
                                                            chart.data.datasets[0].data = voteCounts;

                                                            // 차트 다시 그리기
                                                            chart.update();
                                                        }
                                                    };
                                                    voteCountsXHR.send();
                                                }
                                            };
                                            xhr.send("num=" + num + "&optionId=" + optionId);
                                        } else {
                                            alert("투표 옵션을 선택하세요.");
                                        }
                                    });

                                </script>
                            </div>
                        </div>

            </div>
        </div>

        <!-- Features End -->

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