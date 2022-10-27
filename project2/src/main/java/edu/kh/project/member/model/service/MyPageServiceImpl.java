package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.member.model.dao.MyPageDAO;
import edu.kh.project.member.model.vo.Member;

@Service // bean 등록
public class MyPageServiceImpl implements MyPageService{

 
    @Autowired
    private MyPageDAO dao;
    
    @Autowired
    private BCryptPasswordEncoder bcrypt;
    
    // 회원 정보 수정 서비스
    @Transactional //트랜잭션
    @Override
    public int updateInfo(Member inputMember) {
       int result = dao.updateInfo(inputMember);
       
       
        
        return result;
    }
}
