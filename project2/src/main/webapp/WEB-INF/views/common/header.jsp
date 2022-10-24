<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



 <header>
            <section>
                <a href="/">
                    <img src="/resources/images/logo.jpg" id="home-logo" alt="kh정보교육원 마크">
                </a>
            </section>
            <section>
                <article class="search-area">
                    <!-- 내부 input 태그의 값을 서버 또는 페이지로 전달(제출)-->
                    <form action="#">
                        <fieldset>
                            <input type="text" id="query" name="query"
                                placeholder="검색어를 입력해주세요." autocomplete="off">
                                
                                <!-- 검색 버튼-->
                                <button type="submit" id="search-btn" class="fa-solid fa-magnifying-glass">
                                    <!-- <i class="fa-solid fa-magnifying-glass"></i> -->
                                    <!-- class내용을 버튼 class에다가 대입 -->
                                    <!-- 특수문자처럼 들어간다?? -->
            
            
                                </button>
                        </fieldset>
            
                    </form>
                </article>
            </section>
            <section></section>

            <%-- 헤더 오른쪽 상단 메뉴 --%>
            <%-- 메인 페이지 | 로그인 --%>
            <%-- 유저일 ▼  --%>
            <%-- 내정보 --%>
            <%-- 로그아웃 --%>
            <div id="header-top-menu">
            <c:choose>
                <%-- 로그인 X인 경우 --%>
                <c:when test="${empty sessionScope.loginMember}">
                <a href="/">메인 페이지</a>
                |
                <a href="/member/login">로그인</a>
                </c:when>


                <%-- 로그인 0인 경우 --%>
                <c:otherwise>
                    <%-- label = input태그의 이름같은 것 --%>
                    <label for="header-menu-toggle">
                        ${loginMember.memberNickname}
                        <i class="fa-solid fa-caret-down"></i> <%-- 화살표 모양 이미지 --%>
                    </label>

                    <input type="checkbox" id="header-menu-toggle">

                    <div id="header-menu">
                        <a href="/member/myPage/info">내정보</a>
                        <a href="/member/logout">로그아웃</a>  <%--  --%>
                    </div>
                
                </c:otherwise>
            </c:choose>

            </div>
            
        




        </header>

        <nav>
            <ul>
                <%-- <li><a href="#">공지사항</a></li>
                <li><a href="#">자유 게시판</a></li>
                <li><a href="#">질문 게시판</a></li>
                <li><a href="#">FAQ</a></li>
                <li><a href="#">1:1 문의</a></li> --%>

                <c:forEach var="boardType" items="${boardTypeMap}">
                <%--
                     EL을 이용해서 Map 데이터를 다루는 방법
                     key를 얻어오고 싶으면 ${변수명.key}
                    value               >> ${변수명.value}

                 --%>
                <li>  <a href="/board/${boardType.key}/list">${boardType.value}   </a></li>

                </c:forEach>

    
            </ul>
    
        </nav>