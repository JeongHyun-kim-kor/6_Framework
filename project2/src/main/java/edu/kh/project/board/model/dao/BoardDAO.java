package edu.kh.project.board.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO {
    
    @Autowired
    private SqlSessionTemplate sqlSession; 
//    private SqlSessionTemplate == SqlSession+  트랜잭션 제어기능  

}
