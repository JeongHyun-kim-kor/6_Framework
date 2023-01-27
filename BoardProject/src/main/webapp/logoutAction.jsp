<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP 게시판 웹사이트</title>
</head>
<body>
    <%
        session.invalidate();
    %>
    <script>
        alert('로그아웃되었습니다.');
        location.href='main.jsp';
    </script>
</body>
</html>