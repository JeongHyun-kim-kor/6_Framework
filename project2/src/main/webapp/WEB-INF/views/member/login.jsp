<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 페이지</title>

    <link rel="stylesheet" href="/resources/css/login-style.css">

    <!-- fontawesome 사이트 아이콘 이용 -->
    <script src="https://kit.fontawesome.com/f7459b8054.js" 
    crossorigin="anonymous"></script>

</head>
<body>
    <main>
        <section class="logo-area">
            <a href="/">
                <img src="../resources/images/logo.jpg">
            </a>


        </section>
                                    
        <form action="/member/login"  method="post">
            <section class="input-box">
                <!-- required 속성 : form태그 제출 시 해당 input태그에 값이 필수로 존재하는 검사 -->
                <input type="text" name="inputEmail" placeholder="Email" required value="${cookie.saveId.value}">
                
            </section>
            <section class="input-box">
                <!-- required 속성 : form태그 제출 시 해당 input태그에 값이 필수로 존재하는 검사 -->
                <input type="password" name="inputPw" placeholder="Password" required>
                
            </section>

            <button class="login-btn">Login</button>

            <%-- 쿠키에 saveId가 있는 경우 변수 생성--%>
            <c:if test="${!empty cookie.saveId.value}">
                <c:set var="temp" value="checked" />
            
            </c:if>


            <div class="saveId-area">
                <input type="checkbox" name="saveId" id="saveId" ${temp}>
                <label for="saveId">
                    <i class="fas fa-check"></i> 아이디 저장
                </label>
            </div>

            <p class="text-area">
                <a href="/member/signUp">회원 가입</a>
                |
                <a href="#">ID/PW 찾기</a>

            </p>

        </form>

    </main>

    <%-- session scop에 message속성이 존재하는 경우
  alert창을 이용해서 내용을 출력 --%>
  <%-- 어디서든 재활용하려고 header보다 footer에 작성해두는것이 나중에찾기쉬워서
  hraeder작업에 영향을 주지않으려고(hrader에 작성할경우 다른 코드의진행이 멈출수잇음) --%>
    
  <c:if test="${not empty sessionScope.message}">

     <script>
        alert("${sessionScope.message}")
    </script>

    <%-- message 1회 출력 후 session scope에서 삭제 --%>
    <%-- 세션에 남아있어서 계속 알림이 뜸(로그인하든 로그아웃하든) --%>
    <c:remove var="message" scope="session" />
  </c:if>







</body>
</html>