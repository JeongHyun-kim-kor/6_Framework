<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지</title>

    <link rel="stylesheet" href="/resources/css/main-style.css">
    <link rel="stylesheet" href="/resources/css/myPage-style.css">


 <!--  fontawesome 사이트 아이콘 이용   -->
    <script src="https://kit.fontawesome.com/f7459b8054.js" crossorigin="anonymous"></script>

</head>
<body>
    <main>

        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="myPage-content">

            <jsp:include page="/WEB-INF/views/member/sideMenu.jsp"/>


            <section class="myPage-main"> 

                <h1 class="myPage-title">프로필</h1>
                <span class="myPage-subject">
                    프로필 이미지를 변경할 수 있씁니다.    
                </span>

                <form action="#" method="post" name="myPage-frm">

                    <div class="profile-image-area">
                        <img id="profile-image" src="/resources/images/user.png" alt="">
                    </div>
                    <span id="delete-image">&times;</span>

                    <div class="profile-btn-area">
                        <label for="image-input">이미지 선택</label>

                        <!--accept 속성 : 업로드 가능한 파일의 타입을 제한하는 속성-->
                        <input type="file" name="profileImage" id="image-input" accept="image/*">

                        <button class="">변경하기</button>
                    </div>


                    <div class="myPage-row">
                        <label>이메일</label>
                        <span>user01@kh.or.kr</span>
                    </div>

                    <div class="myPage-row">
                        <label>가입일</label>
                        <span>22년 10월 27일 10시 39분 12초</span>
                    </div>

                
                </form>
            </section>

        </section>




    </main>


    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <!-- 다음 주소 api 추가 -->
    <script src ="/resources/js/member/myPage.js"></script>

</body>
</html>