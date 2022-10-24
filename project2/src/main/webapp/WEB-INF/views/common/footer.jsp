<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<footer>
        <p>
            Copyright &copy; KH Information Educational Institute A-Class
            
        </p>

        <article>
            <a href="#">프로젝트 소개</a>
            <span> | </span>
            <a href="#">이용약관</a>
            <span> | </span>

            <a href="#">개인정보 처리방침</a>
            <span> | </span>

            <a href="#">고객센터</a>

        </article>
</footer>



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