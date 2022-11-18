package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.board.model.vo.Board;


public interface BoardService {

    
    /** 게시판 이름 목록 조회
     * @return boardTypeList
     */
    List<Map<String, Object>> selectBoardType();

    /**
     * @param boardCode
     * @param cp
     * @return map
     */
    Map<String, Object> selectBoardList(int boardCode, int cp);

    /**
     * @param boardNo
     * @return
     */
    Board selectBoardDetail(int boardNo);


    
}
