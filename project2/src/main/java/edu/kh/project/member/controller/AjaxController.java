package edu.kh.project.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.member.model.service.AjaxService;

@Controller // 역할 : 요청 -> 알맞은 서비스 -> 결과 반환 -> 알맞은 view 응답 제어 + bean 등록
public class AjaxController {

    @Autowired
    private AjaxService service;
    
    
    // 이메일 중복 검사 
    @GetMapping("/emailDupCheck")
    @ResponseBody // 반환된 값을 jsp경로가 아닌 값 자체로 인식
    public int emailDupCheck(String memberEmail) {
        // data : {"memberEmail" : memberEmail.value}
        
//        System.out.println(memberEmail);
        
        // 이메일 중복검사 서비스 호출
        int result = service.emailDupCheck(memberEmail);
        
        // return 0.jsp??일때? 강의 다시
        // /WEB-INF/views/0.jsp forward
        
        // @ResponseBody 어노테이션덕분에
        // result 가 View Resolver로 전달되지 않고
        //호출해떤 ajax함수로 반환됨
        return result;
    }

}