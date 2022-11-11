package edu.kh.project.member.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.AjaxDAO;
import edu.kh.project.member.model.vo.Member;

@Service // 비즈니스 로직 처리 역할(트랜잭션..) + bean 등록
public class AjaxServiceImpl implements AjaxService{
    
    @Autowired
    private AjaxDAO dao;
    
    //이메일 중복 검사
    @Override
    public int emailDupCheck(String memberEmail) {
        return dao.emailDupCheck(memberEmail);
    }
    
    // 닉네임 중복 검사
    @Override
    public int nicknameDupCheck(String memberNickname) {

        return dao.nicknameDupCheck(memberNickname);
    }

    @Override
    public Member selectEmail(String email) {
        return dao.selectEmail(email);
    }
    /**
     * 회원 목록 조회
     */
    @Override
    public List<Member> selectMemberList() {
        // TODO Auto-generated method stub
        return dao.selectMemberList();
    }
}
