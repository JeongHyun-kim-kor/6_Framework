package edu.kh.project.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.vo.Member;

@Repository  // 퍼시스턴스 레이어, 영속성을 (파일,DB)가진 클래스 + bean 등록
public class MemberDAO {

	// DBCP + MyBatis 이용 객체 DI(의존성 주입)
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/** 로그인 DAO
	 * @param memberEmail
	 * @return loginMember
	 */
	public Member login(String memberEmail) {
//		sqlSession.selectOne("mapper이름.태그id", SQL작성시 필요한 값들);
		return sqlSession.selectOne("memberMapper.login", memberEmail);
	}
	
    /** 회원 가입 DAO
     * @param inputMember
     * @return result
     */
    public int signUp(Member inputMember) {

        
            
//        return sqlSession.insert("매퍼이름.태그id", inputMember);
        return sqlSession.insert("memberMapper.signUp", inputMember);
                                               // signUo >> 임의로 지어주고 member-mapper.xml로 가서 작성
//        return 0;
    }
	
	
	
	
}
