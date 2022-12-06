
// 썸네일 클릭시 Modal창으로 출력하기
// 즉시실행함수 > 속도가 빠르고 , 변수중복으로부터 벗어날 수 있다.
(()=> {
    const thumbnailList = document.getElementsByClassName("list-thumbnail");

    if(thumbnailList.length > 0 ){ //썸네일이 존재할 경우

        // Modal 관련 요소 얻어오기
        const modal = document.querySelector(".modal"); // 화면을 회색으로 덮는 창? == modal
        const modalClose = document.getElementById("modal-close");
        const modalImage = document.getElementById("modal-image");

        // 썸네일 요소에 클릭 이벤트 추가
        for(let th of thumbnailList){
            th.addEventListener("click", () => {

                // modal 창에 show 클래스가 없으면 추가 (있으면 삭제)
                modal.classList.toggle("show");

                // 클릭한 썸네일의 src속성 값을 얻어와
                // modal-image의 src속성으로 세팅

                // modalImage.setAttribute("src", 속성 값)
                modalImage.setAttribute("src",  th.getAttribute("src"));
            })
        }

        // x버튼 동작

        modalClose.addEventListener("click", () => {
            
            // hide클래스를 추가해서 0.5초동안 투명해지는 애니메이션 수행
            modal.classList.toggle("hide");

            // 0.5초 후에 show, hide 클래스를 모두 제거
            // setTimeout(함수, 지연시간);
            setTimeout(() => {
                modal.classList.remove("show", "hide");} , 500)
            });
            
            // modal.classList.toggle("show");
            // 


    }
    

})();

// 글쓰기 버튼
(()=>{
    const insertBtn = document.getElementById("insertBtn");

    if(insertBtn != null){ // 버튼이 존재할 때만
        insertBtn.addEventListener("click", () => {
            location.href = "/write/" + boardCode; /* boardList.jsp에서 선언한 전역변수 boardCode */
            
        })

    }
})();


// 11.25 3교시

// 검색을 한 경우 검색창에 검색한 key와 query 남겨놓기
(()=>{
    const select = document.getElementById("search-key");
    const input = document.getElementById("search-query");
    const option = document.querySelector("#search-key > option")

    if(select != null){ // 검색창이 존재할 때
        const params = new URL(location.href).searchParams;
        // 주소에서 쿼리스트링만 분리한 객체

        const key = params.get("key");
        const query = params.get("query");

        // input 이전 검색어를 값으로 추가
        input.value = query;

        // select에서 이전 검색한 key의 값과 일치하는 option태그에 
        // selected 속성 추가
        for(let op of option){
            // option의 value와 key가 일치할 때
            if(op.value == key){
                //op.setAttribute("selected", true)
                op.selected = true;

            }
        }


    }
})();