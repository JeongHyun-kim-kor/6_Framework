package edu.kh.project.chatting.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.chatting.model.vo.ChattingRoom;

public interface ChattingService {

    /** 채팅방이 있는지 확인
     * @param map
     * @return chattingNo
     */
    int checkChattingNo(Map<String, Integer> map);

    /** 채팅방 생성
     * @param map
     * @return chattingNo(채팅방 번호)
     */
    int createChattingRoom(Map<String, Integer> map);

    /** 참여중인 채팅방 조회
     * @param memberNo
     * @return roomList
     */
    List<ChattingRoom> selectRoomList(int memberNo);

}
