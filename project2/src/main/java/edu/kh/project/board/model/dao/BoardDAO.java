package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.vo.Board;
import edu.kh.project.board.model.vo.Pagination;

@Repository
public class BoardDAO {
    
    @Autowired
    private SqlSessionTemplate sqlSession; 
//    private SqlSessionTemplate == SqlSession+  트랜잭션 제어기능  

    /** 게시판 이름 목록 조회
     * @return boardTypeList
     */
    public List<Map<String, Object>> selectBoardType() {

            
        return sqlSession.selectList("boardMapper.selectBoardType");
    }

    /** 게시글 수 조회
     * @param boardCode
     * @return listCount
     */
    public int getListCount(int boardCode) {
        
        
        // my이용해서 1행을 조회한다 boardMapper라는 이름의 namespace를 가진 getListcount 라는 Id를 가진 sql을 실행..해서    
        return sqlSession.selectOne("boardMapper.getListCount", boardCode);
    }


    /** 특정 게시판 목록 조회
     * @param pagination
     * @param boardCode
     * @return 
     */
    public List<Board> selectBoardList(Pagination pagination, int boardCode) {

        // RowBounds 객체(MyBatis)
        // - 여러 행 조회 결과 중 특정 위치부터 몇개 지정된 행의 개수만 조회하는 객체
//                            (몇행을 건너뛸 것인가?)
        
          int offset = (pagination.getCurrentPage() -1) * pagination.getLimit(); ; // 현재 몇페이지 인가?
        
        
//        RowBounds rowBounds = new  RowBounds(offset, limit);
          RowBounds rowBounds = new  RowBounds(offset, pagination.getLimit());
                  
          
                
        
        return sqlSession.selectList("boardMapper.selectBoardList", boardCode, rowBounds);
                                      //1) namespace.id
                                                               //2. 파라미터
                                                                            // 3. RowBounds객체 (항상 3번째 매개변수자리에 위치해야 한다.)
                                                               // 파라미터가 없을 경우 null 대입
    }

    
    
    
    
    
    
}
