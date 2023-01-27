<%@page import ="java.io.PrintWriter"%>
<%@page import ="board.model.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="board" class="board.model.vo.Board" scope="page"/>
<jsp:setProperty name="board" property="boardTitle"/>  <!-- property = jsp의 name값?  -->
<jsp:setProperty name="board" property="boardContent"/>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%  
        // 현재 세션 상태 체크
        String userId = null;
        if(session.getAttribute("userId") !=null){
            userId = (String)session.getAttribute("userId");
        }

        // 로그인이 되어있지 않다면
        if(userId == null){
            PrintWriter script = response.getWriter();
            script.println("<script>");
        	script.println("alert('로그인을 해주세요')");
        	script.println("location.href='login.jsp'");
        	script.println("</script>");
        } else{  // 로그인된 상태 -> 글을 쓸 수 있다.

            if(board.getBoardTitle() == null || board.getBoardContent() == null){
            	PrintWriter script = response.getWriter();
                script.println("<script>");
            	script.println("alert('내용을 채워주세요')");
            	script.println("history.back()");
            	script.println("</script>");
            }else{
            	// 제목과 내용이 입력이 되었다면
            	BoardDAO boardDAO = new BoardDAO();
            	int result = boardDAO.wrtie(board.getBoardTitle(), userId, board.getBoardContent());
            	if(result == -1){
            		PrintWriter script = response.getWriter();
                    script.println("<script>");
                	script.println("alert('글쓰기 실패')");
                	script.println("history.back()");
                	script.println("</script>");
            	}else{
            		PrintWriter script = response.getWriter();
                    script.println("<script>");
                	script.println("alert('글쓰기 성공')");
                	script.println("location.href='board.jsp'");
                	script.println("</script>");
            	}
            	
            	
            	
            }

        }

      

        

    %>
    
</body>
</html>