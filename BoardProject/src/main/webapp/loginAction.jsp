<%@page import ="java.io.PrintWriter"%>
<%@page import ="user.model.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<jsp:useBean id="user" class="user.model.vo.User" scope="page"/>
<jsp:setProperty name="user" property="userId"/>  <!-- property = jsp의 name값?  -->
<jsp:setProperty name="user" property="userPassword"/>

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
        UserDAO userDAO = new UserDAO();
    	int result = userDAO.login(user.getUserId(), user.getUserPassword());
    	
    	if(result == 1){
    		PrintWriter script = response.getWriter();
    		script.println("<script>");
    		script.println("alert('로그인성공')");
    		script.println("location.href='main.jsp'");
    		script.println("</script>");
    	}else if(result == 0){
    		PrintWriter script = response.getWriter();
    		script.println("<script>");
    		script.println("alert('비밀번호가 틀립니다.')");
    		script.println("history.back()");
    		script.println("</script>");
    	}else if(result == -1){
    		PrintWriter script = response.getWriter();
    		script.println("<script>");
    		script.println("alert('존재하지 않는 아이디')");
    		script.println("history.back()");
    		script.println("</script>");
    	}else if(result == -2){
    		PrintWriter script = response.getWriter();
    		script.println("<script>");
    		script.println("alert('데이터베이스 오류')");
    		script.println("history.back()");
    		script.println("</script>");
    	}
    	
    %>
    
</body>
</html>