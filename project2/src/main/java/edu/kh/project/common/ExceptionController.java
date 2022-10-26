package edu.kh.project.common;

import java.sql.SQLException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 예외 처리용 컨트롤러
@ControllerAdvice 
public class ExceptionController {
    
    @ExceptionHandler(Exception.class)//      동작X(주석처리하면)
    public String exceptionHandler(Exception e, Model model) {
        
        // 매개변수 Exception e : 발생한 예외 전달 받는 매개변수
        e.printStackTrace();
        
        model.addAttribute("errorMessage", "서비스 이용 중 문제가 발생했습니다.");
        model.addAttribute("e", e);
        
        
        return "common/error";
    }
// 이렇게 예외별로 나눠서도 처리 가능    
/*    // 데이터베이스 관련만 에러메시지 뜨게하고싶다?
    @ExceptionHandler(SQLException.class)//      동작X(주석처리하면)
    public String exceptionHandler(SQLException e, Model model) {
        
        // 매개변수 Exception e : 발생한 예외 전달 받는 매개변수
        e.printStackTrace();
        
        model.addAttribute("errorMessage", "서비스 이용 중 문제가 발생했습니다.");
        model.addAttribute("e", e);
        
        
        return "common/error";
    }  */
    
    
}
