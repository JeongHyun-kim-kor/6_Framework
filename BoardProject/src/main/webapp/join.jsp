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

    
    <!-- 회원가입 양식 -->
        <div class="container">
            <div class="col-lg-4">
                <!-- 점보트론 : 특정 콘텐츠, 정보를 두드러지게 하는 큰 박스-->
                <div class="jumbotron" style="padding-top:20px;">
                    <form action="joinAction.jsp" method="post">
                        <h3 style="text-align:center;">회원가입 화면</h3>
                        <div class ="form-group">
                            <input type="text" class="form-control" placeholder="아이디" name="userId" maxlength="20">
                        </div>
                        <div class ="form-group">
                            <input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
                        </div>
                        <div class ="form-group">
                            <input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20">
                        </div>
                        <div class="form-group" style="text-align: center;">
                            <div class="btn-group" data-toggle="buttons">
                                <label class="btn btn-primary active">
                                    <input type="radio" name="userGender" autocomplete="off" value="남자" checked>남자
                                </label>
                                <label class="btn btn-primary active">
                                    <input type="radio" name="userGender" autocomplete="off" value="여자" checked>여자
                                </label>
                            
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" placeholder="이메일" name="userEmail" maxlength="20">
                        </div>
                        <input type="submit" class="btn btn-primary form-control" value="회원가입">

                    </form>
                </div>
            </div>
        
        </div>



    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    

</body>
</html>