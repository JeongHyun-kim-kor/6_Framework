package edu.kh.project.chatting.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.chatting.model.vo.ChattingRoom;

@Repository
public class ChattingDAO {

    @Autowired
    private SqlSessionTemplate sqlSession;
    public int createChattingRoom;

    /** 채팅방 있는지 확인
     * @param map
     * @return chattingNo
     */
    public int checkChattingNo(Map<String, Integer> map) {
        return sqlSession.selectOne("chattingMapper.checkChattingNo", map);
    }

    /** 채팅방 생성
     * @param map 
     * @return chattingNo
     */
    public int createChattingRoom(Map<String, Integer> map) {
        int result = sqlSession.insert("chattingMapper.createChattingRoom", map);
        int chattingNo = 0;
        if(result > 0)  chattingNo = (int)map.get("chattingNo");
        return chattingNo;
    }

    /** 참여중인 채팅방
     * @param memberNo
     * @return roomList
     */
    public List<ChattingRoom> selectRoomList(int memberNo) {
        // TODO Auto-generated method stub
        return sqlSession.selectList("chattingMapper.selectRoomList", memberNo);
    }
}
