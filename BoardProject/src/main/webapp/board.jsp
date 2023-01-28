<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.io.PrintWriter" %>
<%@ page import="board.model.dao.BoardDAO" %>
<%@ page import="board.model.vo.Board" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 컴퓨터, 폰 어디에서도 해상도에 맞게 알아서 디자인이 변경되는 반응형 웹 메타 태그-->
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!--  css폴더 안에 bootstrap.css를 참조 >>  디자인-->
<link rel="stylesheet" href="css/bootstrap.css">

<title>JSP 게시판 웹 사이트</title>
<style type="text/css">
    a, a:hover{
        color:#000000;
        text-decoration: none;
    }

</style>
</head>
<body>
    <%
        // 메인페이지로 이동시 세션에 값이 담겨있는지 확인하는 코드
        String userId = null;
        if(session.getAttribute("userId") != null){
            userId = (String)session.getAttribute("userId");
        }
        int pageNumber = 1; // 기본은 1페이지로 할당
        
        if(request.getParameter("pageNumber") != null){
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        }
        //만약 pageNumber가 파라미터로 넘어와 존재한다면(Object형/?)
        // int로 캐스팅해주고() 그 값을 pageNumber 변수에 저장한다.

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

    <%-- 게시판 메인 페이지 영역  --%>
    <div class="container">
        <div class="row">
            <table class="table table-striped" style="text-align: center; border : 1px solid #dddddd">
                <thead>
                    <tr>
                        <th style="background-color: #eeeeee; text-align : center;">번호</th>
                        <th style="background-color: #eeeeee; text-align : center;">제목</th>
                        <th style="background-color: #eeeeee; text-align : center;">작성자</th>
                        <th style="background-color: #eeeeee; text-align : center;">작성일</th>
                    </tr>
                </thead>
                <tbody>
                <%-- ,게시글 목록 뽑아오기  --%>
                    <%
                        BoardDAO boardDAO = new BoardDAO();
                        ArrayList<Board> list = boardDAO.getList(pageNumber);
                        for(int i = 0 ; i < list.size(); i++){
                    %>


                    <tr>
                        <td><%= list.get(i).getBoardId()%>   </td>
                        <%-- 제목눌렀을 때 해당 글 볼 수 있게 링크 걸어두기 --%>
                        <td><a href="view.jsp?boardId=<%=list.get(i).getBoardId()%>">
                            <%= list.get(i).getBoardTitle() %></td>
                        <td><%= list.get(i).getUserId() %></td>
                        <td><%= list.get(i).getBoardDate() %></td>
                        
                    </tr>


                    <%
                        }
                    %>
                </tbody>
            </table>

            <%-- 페이징 처리 영역 --%>
            <%
                if(pageNumber != 1){

                
            %>  
            <a href="board.jsp?pageNumber=<%=pageNumber - 1 %>"
            class="btn btn-success btn-arraw-left"> 이전</a>

            <%
                }if(boardDAO.nextPage(pageNumber +1)){
            %>
                <a href="board.jsp?pageNumber=<%=pageNumber +1%>"
                class="btn btn-success btn-arraw-left">다음</a>
            <%
                }
            %>
            <%-- 글쓰기 버튼 --%>
            <a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
        </div>
    </div>
     <%-- 게시판 메인 페이지 영역 끝  --%>

   <%-- <div class="container">
        <div class="col-lg-4">

            <div class="jumbotron" style="padding-top:20px;">
                <form method="post" action="loginAction.jsp">
                    <h3 style="text-align:center;">로그인 화면</h3>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="아이디" name="userId" maxlength="20">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">
                    </div>
                    <input type="submit" class="btn btn-primary form-control" value="로그인">
                </form>
            </div>

        </div>
   </div> --%>


    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
    

</body>
</html>