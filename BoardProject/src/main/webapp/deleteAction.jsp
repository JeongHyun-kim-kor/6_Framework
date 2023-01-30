<%@ page import="java.io.PrintWriter"%>
<%@ page import="board.model.vo.Board" %>
<%@ page import="board.model.dao.BoardDAO" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% request.setCharacterEncoding("UTF-8"); %>
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
        }

        int boardId = 0;
        if(request.getParameter("boardId") != null){
            boardId = Integer.parseInt(request.getParameter("boardId"));
        }

        if(boardId == 0){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('유효하지 않은 글 입니다.')");
            script.println("location.href='board.jsp'");
            script.println("</script>");
        }
        // 해당 'boardId'에 대한 게시글을 가져온다음 다음 세션을 통하여 작성자 본인이 맞는지 체크한다.
        Board board = new BoardDAO().getBoard(boardId);
        if(!userId.equals(board.getUserId())){
            PrintWriter script = response.getWriter();
            script.println("<script>");
            script.println("alert('권한이 없습니다.')");
            script.println("location.href='board.jsp'");
            script.println("</script>");
        

            // 삭제하기 로직 수행
            }else{
                BoardDAO boardDAO = new BoardDAO();
                int result = boardDAO.delete(boardId);

                // DB오류인 경우
                if(result == -1){
                    PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('글수정실패')");
                script.println("history.back()");
                script.println("</script>");
                } else{
                    PrintWriter script = response.getWriter();
                script.println("<script>");
                script.println("alert('글삭제하기 성공')");
                script.println("location.href='board.jsp'");
                script.println("</script>");
                }
            }
        

      

        

    %>
    
</body>
</html>