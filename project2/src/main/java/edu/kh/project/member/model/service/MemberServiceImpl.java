package edu.kh.project.member.model.service;

import org.springframework.stereotype.Service;

import edu.kh.project.member.model.vo.Member;

// @Service : bean 등록 시 이름을 클래스명으로 등록(memberServiceImpl)
// @Servuce("이름") : bean 등록시 지정된이름("이름")으로 등록


@Service // Service 레이어, 비즈니스 로직을 가진클래스임을 명시 + bean 등록
public class MemberServiceImpl implements MemberService {

	//로그인서비스
	@Override
	public Member login(Member inputMember) {

		//DAO코드
		
		return null;
	}
	
}
