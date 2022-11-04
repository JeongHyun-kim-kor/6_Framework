//JS 객체를 이용한 유효성 검사 결과 저장 객체
// JS 객체 ={"K" V, "K": V ...} (Map형식)

// 변수명.key 또는 변수명["key"] 를 이용하면 객체 내부 
const checkObj = {
    "memberEmail"    : false,
    "memberPw"       : false,
    "memberPwConfirm": false,
    "memberNickname" : false,
    "memberTel"      : false
}

//회원 가입 양식이 제출되었을 때 
document.getElementById("signUp-frm").addEventListener("submit", function(event){


    // // 이메일이 유효한가?
    // if(!emailCheck){   // 유효하지 않은 경우
    //     alert("이메일이 유효하지 않습니다.")
    //     event.preventDefault();

    // }

    //다시 쓴다.
    // checkObj의 속성 중 하나라도 false가 있다면 제출 이벤트 제거

    // for in 구문 : 객체의 key값을 순서대로 접근하는 반복문
    // [작성법]
    // for(let 변수명 in 객체명 )  
             //변수명 = key라 적어두면 보기 편하다
    // -> 객체에서 순서대로 let을 하나씩 꺼내 왼쪽 변수에 저장

    for(let key in checkObj){
        
        let str; 

        // checkObj 속성중 하나를 꺼내 값을 검사했는데 false인 경우(if문)
        if(!checkObj[key]){

            switch(key){
                case "memberEmail"       : str = "이메일이 유효하지 않습니다."; break;
                case "memberPw"          : str = "비밀번호가 유효하지 않습니다,"; break;
                case "memberPwConfirm"   : str = "비밀번호 확인이 유효하지 않습니다,"; break;
                case "memberNickname"   : str = "닉네임이 유효하지 않습니다,"; break;
                case "memberTel"        : str ="전화번호가 유효하지 않습니다."; break;
            }
            alert(str); // 대화상자 출력
            // 유효하지 않은 입력창으로 포커스 이동
            document.getElementById(key).focus();

            event.preventDefault(); // 제출 이베트 제거
            return ; // 함수 종료
        }
    }




})

const signUpForm = document.getElementById("signUp-frm");
// 이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.getElementById("emailMessage");
const receiveCheck = emailMessage.nextElementSibling;

//input 이벤트 : input 태그에 입력이 되었을 경우(모든 입력 인식)
memberEmail.addEventListener("input", function(){
    // 문자가 입력되지 않은 경우
    if(memberEmail.value.trim().length == 0) {
        emailMessage.innerText = "메일을 받을 수 있는 이메일을 입력해주세요.";
        memberEmail.value = "";

        // confirm, error 클래스 전부 제거 -> 검정 글씨로 만들기
        emailMessage.classList.remove("confirm", "error");

        checkObj.memberEmail = false;

        return; // 글자가 없는 경우, 이 조건식을 수행한 후 함수를 종료
    }
    
    // ^[A-Za-z\d\-\_]{4,}@[가-힣\w\-\_]+(\.\w+){1,3}$
    // 영어 대문자, 영어 소문자, 숫자(\d), -(하이픈), _(언더바)로 구성된 4글자 이상의 단어
    // ..로 시작해서
    // @
    // 아무 단어 및 -, _, 한글을 포함해 한 글자 이상
    // (.한글자 이상 단어) 조합(예 : .com)이 1번에서 3번 반복
    // ..으로 끝나는 구조만 허용함
    const regEx = /^[A-Za-z\d\-\_]{4,}@[가-힣\w\-\_]+(\.\w+){1,3}$/;

    if(regEx.test(memberEmail.value)) { // 유효한 경우
        emailMessage.innerText = "유효한 이메일 형식입니다.";
        emailMessage.classList.add("confirm");
        emailMessage.classList.remove("error");

        checkObj.memberEmail = true;
        
    } else { // 유효하지 않은 경우
        emailMessage.innerText = "이메일 형식이 유효하지 않습니다.";
        emailMessage.classList.add("error")
        emailMessage.classList.remove("confirm");

        checkObj.memberEmail = false;

    }
    
    


});


// 1104 3교시

// 비밀번호 유효성 검사
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const pwMessage = document.getElementById("pwMessage");

// 비밀번호 입력시 
memberPw.addEventListener("input" , function(){

    // 비밀번호가 입력되지 않은 경우
    if(memberPw.value.trim().length == 0){
        pwMessage.innerText = "영어, 숫자, 특수문자(!,@,#,-,_) 6~20글자 사이로 입력해주세요"
        memberPw.value="";

        pwMessage.classList.remove("comfirm", "error"); //검정글씨로 변환

        checkObj.memberPw = false;
        return;
    }

    // 비밀번호 정규표현식 검사
    const regEx = /^[a-zA-Z\d!@#-_]{6,20}$/;

    if(regEx.test(memberPw.value)){ //유효한 비밀번호라면
        checkObj.memberPw = true;

        // 유효한 비밀번호 + 확인에 작성 X
        if(memberPwConfirm.value.trim().length == 0 ){
        pwMessage.innerText = "유효한 비밀번호 형식입니다.";
        pwMessage.classList.add("confirm");
        pwMessage.classList.remove("error");

    } else{ // 유효한 비밀번호 + 비밀번호확인에 작성 O => 같은지 비교

            // 비밀번호가 입력될 때
        // 비밀번호 확인에 작성된 값과 일치하는 경우
        if(memberPw.value == memberPwConfirm.value){
            pwMessage.innerText="비밀번호가 일치합니다."
            pwMessage.classList.add("confirm");
            pwMessage.classList.remove("error");
            checkObj.memberPwConfirm = true;
        } else{
            pwMessage.innerText="비밀번호가 일치하지 않습니다."
            pwMessage.classList.add("error");
            pwMessage.classList.remove("confirm");
            checkObj.memberPwConfirm = false;
        }

    }

    } else { // 유효하지 않음
        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다.";
        pwMessage.classList.remove("confirm");
        pwMessage.classList.add("error");

        checkObj.memberPw = false;

    }
    

});


// 비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener("input", function(){

    // 비밀번호가 유효할 경우에만 비밀번호 == 비밀번호 확인 같은지 비겨
        if(checkObj.memberPw){ //비밀번호가 유효한 경우

            // 비밀번호 == 비밀번호 확인 같은지 검사
            if(memberPw.value == memberPwConfirm.value){
        
                pwMessage.innerText ="비밀번호가 일치합니다.";
                pwMessage.classList.add("confirm");
                pwMessage.classList.remove("error");
        
                checkObj.memberPwConfirm = true;
            } else{
        
                pwMessage.innerText ="비밀번호가 일치하지 않습니다..";
                pwMessage.classList.remove("confirm");
                pwMessage.classList.add("error");
        
                checkObj.memberPwConfirm = false;
            }


        }else{  // 비밀번호가 유효하지 않은 경우
            checkObj.memberPwConfirm = false;

        }


});


// 닉네임 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nickMessage = document.getElementById("nickMessage");

memberNickname.addEventListener("input", function(){

    // 닉네임이 문자가 입력되지 않은 경우
    if(memberNickname.value.trim().length == 0 ){
        nickMessage.innerText="한글,영어, 숫자로만 2~10글자";
        nickMessage.classList.remove("confirm", "error");

        checkObj.memberNickname = false;
        return;

    } 

    // 닉네임 정규표현식 검사
    // const regEx = /^[가-힣a-zA-Z0-9]{2,10}$/;
    const regEx = /^[가-힣\w]{2,10}$/;

    if(regEx.test(memberNickname.value)){ // 유효한 경우

        // 닉네임 중복검사 코드 추가 예정 **

        nickMessage.innerText ="유효한 닉네임 형식입니다.";
        nickMessage.classList.add("confirm");
        nickMessage.classList.remove("error");
        checkObj.memberNickname = true;

    } else{  // 유효하지 않은 경우
        nickMessage.innerText ="닉네임 형식이 유효하지 않습니다.";
        nickMessage.classList.add("error");
        nickMessage.classList.remove("confirm");
        checkObj.memberNickname = false;
    }


});


//221104 5교시 

// 전화번호 유효성 검사
const memberTel = document.getElementById("memberTel"); /* input */
const telMessage = document.getElementById("telMessage"); /* span */

memberTel.addEventListener("input", function(){


    //문자가 입력되지 않은 경우
    if(memberTel.value.trim().length == 0 ){

        telMessage.innerText ="전화번호를 입력해주세요.(- 제외)";
        telMessage.classList.remove("confirm", "error");

        checkObj.memberTel = false;
        return;
    }

    // 전화번호 정규표현식 검사
    const regEx = /^0(1[01679]|2|[3-6][1-5]|70)[1-9]\d{2,3}\d{4}$/;
//                  첫
    // 010 1234 1234
    // 010  321 4321
    // 016 
    // 017 
    // 019
    // 031 042 051 062
    // 070
    if(regEx.test(memberTel.value)){
        telMessage.innerText = "유효한 전화번호 형식입니다.";
        telMessage.classList.add("confirm");
        telMessage.classList.remove("error");
        checkObj.memberTel = true;
    } else {
        telMessage.innerText = "전화번호 형식이 유효하지 않습니다.";
        telMessage.classList.add("error");
        telMessage.classList.remove("confirm");
        checkObj.memberTel = false;
    }


});