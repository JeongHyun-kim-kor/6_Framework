package edu.kh.project.member.model.service;

import edu.kh.project.member.model.vo.Member;

/* Service Interface 사용 이유
 * 
 * 1. 프로젝트에 규칙성을 부여하기 위해서
 * 
 * 2. 클래스간의 결합도를 약화시키기 위함
 * 		-> 유지 보수성 향상(객체 지향적 설계)
 * 
 * 3. AOP를 사용하기 위함
 * 		-> spring-proxy를 이용하여 AOP코드가 동작하는데 
 * 			이 spring-proxy는 Service 인터페이스를 상속받아 동작
 
 */


public interface MemberService {
 // 접점!!
	
	/** 로그인 서비스
	 * @param inputMember(Email / PW)
	 * @return loginMember
	 */
	public abstract Member login(Member inputMember);
	
}
