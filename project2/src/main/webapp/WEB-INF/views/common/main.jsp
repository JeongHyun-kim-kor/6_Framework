<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수업용 프로젝트</title>

    <link rel="stylesheet" href="/resources/css/main-style.css">

    <!-- fontawesome 사이트 아이콘 이용 -->
    <script src="https://kit.fontawesome.com/f7459b8054.js" 
    crossorigin="anonymous"></script>

</head>
<body>

    <main>    
        <%-- header.jsp 추가(포함) --%>
        <%-- 
            jsp 액션 태그 중 include
            - 해당 위치에 page 속성으로 지정된 jsp파일의 내용이 포함됨
            - jsp 파일 경로는 /webapp폴더를 기준으로 작성
        --%>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
  
    

        <section class="content">
            <section class="content-1">
                <div  id="content-1-1">
                    <h3>이메일로 회원정보 조회(AJAX)</h3>

                    이메일 : <input type="text" id="inputEmail">
                    <button id= "selectEmail">조회</button>
                    
                    <!-- 일치하는 이메일이 있을 경우 -->
                <%--      <ul>
                        <li>회우버넌호 : 1 </li>
                        <li>이메일 : user01@kh.or.kr </li>
                        <li> 주소 : 04540,서울시</li>
                        <li>가입일 : 2022년 10월 18일 </li>
                    </ul> --%>

                    <%-- 일치하는 이메일이 없을 경우 --%>
                    <%-- <h4>user01@kh.or.kr은 존재하지 않습니다.</h4> --%>
                    
                    
                </div>   

                <div  id="content-1-2">
                    <h3>10초마다 모든 회원 정보 조회(AJAX)</h3>
                    <table>
                        <thead>
                            <tr>
                                <th>회원번호</th>    
                                <th>이메일</th>
                                <th>탈퇴 여부</th>
                            </tr>
                        </thead>
                        <tbody Id="tbody">
                             <%-- <tr>
                                <th>1</td>
                                <td>user01@kh.or.kr</td>
                                <td>N</td>
                            </tr>
                            <tr class="secession">
                                <th>2</th>
                                <td>user02@kh.or.kr</td>
                                <td>Y</td>
                            </tr> --%>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>회원 수</th>
                                <th colspan="2" id="memberCount" ></th>
                            </tr>
                        </tfoot>
                    </table>

                </div>         

            </section>

            <section class="content-2">


            <%-- 로그인 여부에 따라 출력 화면 변경 --%>


            <c:choose>

            <%-- 로그인 X인 경우 --%>
                <c:when test="${empty sessionScope.loginMember}">
                            <%-- 절대 경로 --%>
                    <form action="/member/login" name="login-frm" method="POST"
                        onsubmit="return loginValidate();">
                                    <%--  false or true  --%>
                        <%-- 
                            form태그의 submit 이벤트를 취소시키는 방법

                            -> 인라인 이벤트 모델의 결과로 false를 리턴하면 
                                제출 이벤트가 취소된다.


                        --%>    






                        <!-- 아이디, 비밀번호, 로그인 버튼 -->
                        <fieldset id="id-pw-area">
                            <section>
                                <input type="text" name="memberEmail" placeholder="아이디" autocomplete="off" value="${cookie.saveId.value}">
                                                                                                        <%-- 쿠키 중 saveId에 저장된 값 --%>
                                <!-- autocomplete ="off" : 자동완성 사용 X-->                                       
                                <input type="password" name="memberPw" placeholder="비밀번호">
            
                            </section>
            
                            <section>
                                <!-- type ="submit"이 기본 값-->
                                <button>로그인</button>
                            </section>
            
            
                        </fieldset>
                        <%-- 쿠키에 saveId가 잇을 경우  --%>
                        <c:if test="${!empty cookie.saveId.value}">
                            <%-- temp 변수 선언--%>
                            <c:set var="temp" value="checked"/>
                            <%-- page scope == page어디서든 사용 가능
                            <%-- if문 나가도 쓸 수 있다. --%>
                        
                        </c:if>


            
                        <!-- label 태그 내부에 input태그를 작성하면 자동으로 서로 연결됨 / for랑 name이랑 일치시키지 않아도됨 -->
                        <label>
                            <input type="checkbox" id="saveId" name="saveId" ${temp} > 아이디 저장
                                                                <%--null or 쿠키?  --%>               
                        </label>
            
                        <!-- 회원가입/ Id/pw찾기 -->
                        <article id="signUp-find-area">
                            <a href="/member/signUp">회원 가입</a>
                            <span> | </span>
                            <a href="#">ID/PW 찾기</a>
                        </article>
            
                    </form>


                </c:when>


                <%-- 로그인 O인 경우 --%>

                <c:otherwise>
                    <article class="login-area">
                        
                        <%-- 회원 프로필 이미지 --%>
                        <a href="/member/myPage/profile">
                            <img id="member-profile" src="/resources/images/user.png">    
                        </a>
                    
                        <%-- 회원 정보 + 로그아웃 버튼 --%>
                        <div class="my-info">
                            <div>
                                <a href="/member/myPage/info" id="nickname">${loginMember.memberNickname}</a>
                                
                                <a href="/member/logout" id="logout-btn">로그아웃


                                </a>
                            </div>
                            
                            <p>${loginMember.memberEmail}</p>
                            
                            
                        </div>
                    </article>


                
                </c:otherwise>
            
            </c:choose>



                            




            </section>
        </section>
        </main>

    <%-- footer.jsp 포함 --%>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    

    <%-- jquery CDN방식으로 추가 --%>
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
    <script src="/resources/js/main.js"></script>

</body>
</html>

