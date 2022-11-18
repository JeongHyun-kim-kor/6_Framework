
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