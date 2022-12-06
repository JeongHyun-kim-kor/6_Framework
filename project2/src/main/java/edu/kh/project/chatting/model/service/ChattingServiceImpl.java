package edu.kh.project.chatting.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.chatting.model.dao.ChattingDAO;
import edu.kh.project.chatting.model.vo.ChattingRoom;

@Service
public class ChattingServiceImpl implements ChattingService {

    @Autowired
    private ChattingDAO dao;
    
    //채팅방이 있는지 확인
    @Override
    public int checkChattingNo(Map<String, Integer> map) {
        // TODO Auto-generated method stub
        return dao.checkChattingNo(map);
    }
    //채팅방 생성
    @Override
    public int createChattingRoom(Map<String, Integer> map) {
        // TODO Auto-generated method stub
        return dao.createChattingRoom(map);
    }
    
    // 참여중인 채팅방 조회
    @Override
    public List<ChattingRoom> selectRoomList(int memberNo) {
        return dao.selectRoomList(memberNo);
    }
}
