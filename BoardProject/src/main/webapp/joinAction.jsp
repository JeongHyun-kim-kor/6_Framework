<%@page import ="java.io.PrintWriter"%>
<%@page import ="user.model.dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% request.setCharacterEncoding("UTF-8"); %>

<jsp:useBean id="user" class="user.model.vo.User" scope="page"/>
<jsp:setProperty name="user" property="userId"/>  <!-- property = jsp의 name값?  -->
<jsp:setProperty name="user" property="userPassword"/>
<jsp:setProperty name="user" property="userName"/>
<jsp:setProperty name="user" property="userGender"/>
<jsp:setProperty name="user" property="userEmail"/>

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
        if(userId != null){
            PrintWriter script = response.getWriter();
        	script.println("<script>");
        	script.println("alert('이미 로그인이 되어있습니다.')");
        	script.println("location.href='main.jsp'");
        	script.println("</script>");
        }

        if(user.getUserId() == null || user.getUserPassword() == null || user.getUserName() == null || user.getUserGender() == null ||
        user.getUserEmail() == null){ // 아이디, 비밀번호, 이름, 이메일, 성별이 하나라도 비어있다면
            PrintWriter script = response.getWriter();
        	script.println("<script>");
        	script.println("alert('입력이 안된 사항이 있씁니다')");
        	script.println("history.back()");
        	script.println("</script>");

        }else{
        	UserDAO userDao = new UserDAO();
        	int result = userDao.join(user);
        	if(result == -1){
                PrintWriter script = response.getWriter();
                script.println("<script>");
            	script.println("alert('이미 존재하는 아이디 입니다.')");
            	script.println("history.back()");
            	script.println("</script>"); 
        	}else{
        		session.setAttribute("userId", user.getUserId());
        		PrintWriter script = response.getWriter();
                script.println("<script>");
            	script.println("alert('회원가입 성공')");
            	script.println("location.href='main.jsp'");
            	script.println("</script>"); 
        	}
        	
        }

    %>
    
</body>
</html>