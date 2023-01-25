<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 컴퓨터, 폰 어디에서도 해상도에 맞게 알아서 디자인이 변경되는 반응형 웹 메타 태그-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!--  css폴더 안에 bootstrap.css를 참조 >>  디자인-->
<link rel="stylesheet" href="css/bootstrap.css">

<title>JSP 게시판 웹 사이트</title>
</head>
<body>
    <%-- <div style="width:200px; height:200px;" class="btn-success">
    하이하이
    </div> --%>
   <!-- 네비게이션 영역-->
   <nav class="navbar navbar-default">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expanded="false">
                <!-- 이 버튼들은 화면이 좁아지면 우측에 나타난다 -->
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            
            <a class="navbar-brand" href="main.jsp">JSP 게시판 웹 사이트</a>
        </div>
    
        <div class ="collapse navbar-collapse" id="bs-example.navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="main.jsp">메인</a></li>
                <li><a href="board.jsp">게시판</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle"
                        data-toggle="dropdown" role="button" aria-haspopup="true"
                        aria-expanded="false">접속하기<span class="caret"></span>

                        <!-- 드랍다운 아이템 영역 -->
                        <ul class="dropdown-menu">
                            <li class="active"><a href="login.jsp">로그인</a></li>
                            <li><a href="join.jsp"></a>회원가입</li>
                        
                        </ul>
                </li>
            </ul>
        </div>
   </nav>


    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    

</body>
</html>