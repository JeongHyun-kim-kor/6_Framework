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
    <%
        // 메인페이지로 이동시 세션에 값이 담겨있는지 확인하는 코드
        String userId = null;
        if(session.getAttribute("userId") != null){
            userId = (String)session.getAttribute("userId");
        }
    %>

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
                <li ><a href="main.jsp">메인</a></li>
                <li class="active"><a href="board.jsp">게시판</a></li>
            </ul>

            <%-- 로그인하지 않았을 때 보여지는 화면  --%>
            <%
                if(userId == null){

                
            %>
            <%-- 헤더 우측에 나타나는 드랍다운 영역 --%>
             <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle"
                        data-toggle="dropdown" role="button" aria-haspopup="true"
                        aria-expanded="false">접속하기<span class="caret"></span>

                        <!-- 드랍다운 아이템 영역 -->
                        <ul class="dropdown-menu">
                            <li><a href="login.jsp">로그인</a></li>
                            <li><a href="join.jsp">회원가입</a></li>
                        
                        </ul>
                </li>
            </ul>

            <%
            // 로그인이 되어있다면
                    }else{
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle"
                        data-toggle="dropdown" role="button" aria-haspopup="true"
                        aria-expanded="false">회원관리<span class="caret"></span>

                        <!-- 드랍다운 아이템 영역 -->
                        <ul class="dropdown-menu">
                            <li><a href="logoutAction.jsp">로그아웃</a></li>
                        
                        </ul>
                </li>
            </ul>

            <%
                    }
            %>
         
        </div>
   </nav>

    <%-- 게시판 글쓰기 양식 --%>
    <div class="container">
        <div class="row">
            <form action="writeAction.jsp" method="post">
                <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
                    <thead>
                        <tr>
                            <th colspan="2" style="background-color: #eeeeee; text-align:center;" >게시판 글쓰기 양식</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="text" class="form-control" placeholder="제목" name="boardTitle"></td>
                        </tr>
                        <tr>
                            <td><textarea class="form-control" placeholder="내용" name="boardContent" style="height:350px"></textarea></td>
                        </tr>
                    </tbody>

                </table>
                <%-- 글쓰기 버튼 --%>
                <input type="submit" class="btn btn-primary pull-rignt" value="글쓰기">
            </form>
        </div>
    
    </div>

    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    

</body>
</html>