package edu.kh.project.board.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.kh.project.board.model.dao.BoardDAO;
import edu.kh.project.board.model.exception.BoardUpdateException;
import edu.kh.project.board.model.vo.Board;
import edu.kh.project.board.model.vo.BoardImage;
import edu.kh.project.board.model.vo.Pagination;
import edu.kh.project.common.Util;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardDAO dao;
    
    // 게시판 이름 목록 조회
    @Override
    public List<Map<String, Object>> selectBoardType() {
        
        
        return dao.selectBoardType();
    }
    
    @Override
    public Map<String, Object> selectBoardList(int boardCode, int cp) {

        // 1. 특정 게시판의 전체 게시글 수 조회(단, 삭제 제외)
        int listCount = dao.getListCount(boardCode);
        
        // 2. 전체 게시글 수 + cp(현재 페이지)이용해서
        // 페이징 처리 객체 생성 > vo패키지에다가
        Pagination pagination = new Pagination(listCount, cp);
        
        
        // 3. 페이징 처리 객체를 이용해서 게시글 목록 조회
        List<Board> boardList = dao.selectBoardList(pagination, boardCode);
        
        
        Map<String ,Object> map = new HashMap<String, Object>();
        map.put("pagination",pagination);
        map.put("boardList", boardList);
        
        return map;
    }
    
    // 게시글 상세 조회 + 이미지 목록 조회 + 댓글 목록 조회
    @Override
    public Board selectBoardDetail(int boardNo) {

//        DAO 3번?  > MyBatis 기능 사용
        
        
        return dao.selectboardDetail(boardNo);
    }
    
    // 조회 수 증가
    @Override
    public int updateReadCount(int boardNo) {
        // TODO Auto-generated method stub
        return dao.updateReadCount(boardNo);
    }
    
    // 좋아요 여부 체크
    @Override
    public int boardLikeCheck(Map<String, Object> map) {
        // TODO Auto-generated method stub
        return dao.boardLikeCheck(map);
    }
    // 좋아요 수 증가(INSERT)
    @Override
    public int boardLikeUp(Map<String, Object> paramMap) {
        // TODO Auto-generated method stub
        return dao.boardLikeUp(paramMap);
    }
    //좋아요 수 증가(DELETE)
    @Override
    public int boardLikeDown(Map<String, Object> paramMap) {
        // TODO Auto-generated method stub
        return dao.boardLikeDown(paramMap);
    }
    
    // 게시글 삭제(UPDATE)
   
   
@Override
public int boardDelete(int boardNo) {
    // TODO Auto-generated method stub
    return  dao.boardDelete(boardNo);

}

// 게시글 삽입
//@Transactional : 예외 발생 시 서비스 내에서 수행한 모든 DML 롤백
//                  단, RuntimeException 발생시에만 롤백
@Transactional(rollbackFor = Exception.class) //모든 예외 발생시  롤백
@Override
public int boardWrite(Board board, List<MultipartFile> imageList, String webPath, String folderPath) throws IOException {

    // 1. 게시글만 삽입
        // 1) XSS(크로스 사이트 스크립트 공격), 개행문자 처리~!
        board.setBoardTitle(Util.XSSHandling(board.getBoardTitle()) );
        board.setBoardContent(Util.XSSHandling(board.getBoardContent()) );
        
        board.setBoardContent(Util.newLineHandling(board.getBoardContent()));
        
        
        // 2) 게시글 삽입 DAO 호출 후 
        //    결과로 삽입된 게시글 번호 반환 받기
        int boardNo = dao.boardWrite(board);
    
    //2. 이미지만 삽입
    if(boardNo > 0) {
        
        // imageList : 실제 파일이 담겨있는 리스트 
        // boardImageList  : DB에 삽입할 이미지 정보만 담겨있는 리스트
        // reNameList : 변경된 파일명만 담겨있는 리스트 
        
        List<BoardImage> boardImageList = new ArrayList<BoardImage>();
        List<String> reNameList = new ArrayList<String>();
        
        // imageList에 담겨있는 파일 중
        // 실제로 업로드된 파일만 분류하는 작업 진행
        
        for(int i = 0; i <imageList.size(); i++) {
            //i번쨰 파일의 크기가 0보다 크다 == 업로드된 파일이 있다.  
            if(imageList.get(i).getSize() > 0) { //업로드된 파일 크기 가져옴
                
                // BoardImage 객체 생성
                BoardImage img = new BoardImage();
                
                // 값 세팅
                img.setImagePath(webPath);
                
//                String reName = Util.fileRename(원본파일명);
                // 원본파일명은 어디서 가져오나? 
                
                // 원본파일명 -> 변경된 파일명으로 
                String reName = Util.fileRename(imageList.get(i).getOriginalFilename());
                
                img.setImageReName(reName);
                reNameList.add(reName); // 변경된 파일명 리스트에 추가
                
                // 원본 파일명
                img.setImageOriginal(imageList.get(i).getOriginalFilename());
                
                img.setBoardNo(boardNo);// 첨부된..게시글 번호
                
                // 인덱스 0번을 썸네일로 하기로 했었다.
                img.setImageOrder(i); // 이미지 순서
                
                
                // boardImageList에 추가
                boardImageList.add(img);
            }//if 끝
        } // for문 끝
        // boardImageList가 비어있지 않다면
        // == 업로드된 파일이 있어서 분류된 내용이 존재한다면
        if(!boardImageList.isEmpty()) {
            
            // DB에 업로드된 파일 정보 INSERT
            int result = dao.insertBoardImageList(boardImageList);
            
            // 삽입 결과 행의 수 == DB에 삽입하려고 분류한 리스트의 크기
            // 전부다 삽입된 경우
            if(result == boardImageList.size()) {
                
                // 파일 변환 작업
                for(int i = 0 ; i <boardImageList.size(); i++) {
                    
                    // 순서 == imageList의 인덱스
                    int index = boardImageList.get(i).getImageOrder(); // 순서얻어옴
                    
                    // 실제 파일로 변환
                    imageList.get(index).transferTo(new File(folderPath+ reNameList.get(i)));
                    // i가 0이었을때 0번째에 있는 순서를 가져와서 
                }
                
            }
        }
        
    }
    
    return boardNo;
}
    
// 게시글 수정
@Transactional(rollbackFor = Exception.class)
@Override
public int boardUpdate(Board board, List<MultipartFile> imageList, String webPath, String folderPath,
        String deleteList) throws Exception {

        // 1. 게시글 부분만 수정
//          1) XSS방지 처리, 개행 문자 처리
    board.setBoardTitle(Util.XSSHandling(board.getBoardTitle()) );
    board.setBoardContent(Util.XSSHandling(board.getBoardContent()) );
    
    board.setBoardContent(Util.newLineHandling(board.getBoardContent()));
//          2) DAO 호출
        int result = dao.boardUpdate(board); // board 안에 제목, 내용 ,게시글번호
    
    
        // 2. 이미지 수정
    
        if(result > 0) { // 정상적으로 게시글이 수정된 경우
            
            // 1) 삭제된 이미지가 있을 경우 삭제 진해
            if(!deleteList.equals("")) {
                // deleteList : "1,2,3" 이런식으로 담겨있음
                
                // WHRER BOARD_NO = 2007 AND IMG_ORDER IN (1,2,3) 이런 구문으로 됨
                String condition = "WHERE BOARD_NO = " + board.getBoardNo()
                                    + " AND IMG_ORDER IN("+deleteList+")";
                
            //2)  DAO 호출
                result = dao.BoardImageDelete(condition);
                
//                result = 0; // 예외발생 테스트용
                
                // 3) 삭제 실패 시
                if(result == 0) {
                    // 강제로 예외를 발생시켜 롤백 수행
                    throw new BoardUpdateException("이미지 삭제 실패");
                    
                    
                }
            } // 1) if문 끝
            
            // 2) imageList에서 실제 업로드된 파일을 찾아 분류하는 작업
        
                
                // imageList : 실제 파일이 담겨있는 리스트 
                // boardImageList  : DB에 삽입할 이미지 정보만 담겨있는 리스트
                // reNameList : 변경된 파일명만 담겨있는 리스트 
                
                List<BoardImage> boardImageList = new ArrayList<BoardImage>();
                List<String> reNameList = new ArrayList<String>();
                
                // imageList에 담겨있는 파일 중
                // 실제로 업로드된 파일만 분류하는 작업 진행
                
                for(int i = 0; i <imageList.size(); i++) {
                    //i번쨰 파일의 크기가 0보다 크다 == 업로드된 파일이 있다.  
                    if(imageList.get(i).getSize() > 0) { //업로드된 파일 크기 가져옴
                        
                        // BoardImage 객체 생성
                        BoardImage img = new BoardImage();
                        
                        // 값 세팅
                        img.setImagePath(webPath);
                        
//                        String reName = Util.fileRename(원본파일명);
                        // 원본파일명은 어디서 가져오나? 
                        
                        // 원본파일명 -> 변경된 파일명으로 
                        String reName = Util.fileRename(imageList.get(i).getOriginalFilename());
                        
                        img.setImageReName(reName);
                        reNameList.add(reName); // 변경된 파일명 리스트에 추가
                        
                        // 원본 파일명
                        img.setImageOriginal(imageList.get(i).getOriginalFilename());
                        
                        img.setBoardNo(board.getBoardNo());// 첨부된..게시글 번호
                        
                        // 인덱스 0번을 썸네일로 하기로 했었다.
                        img.setImageOrder(i); // 이미지 순서
                        
                        
                        // boardImageList에 추가
                        boardImageList.add(img);
                        
                        // 새로 업로드 된 이미지 정보를 이용해서 DB 정보 수정
                        // -> 새로운 이미지가 기존에 존재했는데 수정한건지
                        //   없었는데 추가한건지 현재는 알 수 없다.
                        // --> 일단 순서(IMG_ORDER)를 이용해서 수정
                        //  --> 만약 BOARD_IMG테이블에 IMG_ORDER가 일치하는 행이 없다면
                        //      수정 실패 == 0 반환 == 기존에 없었따 == 새로운 이미지  >> INSERT 필요
                        result = dao.boardImageUpdate(img);  // 일단 업데이트
                        // 11/23 5교시
                        //기존 : 0  2  3
                        //삭제      2    > 0   3
                        //수정 : 0       
                        //추가 :         4
                        if(result == 0) {
                            result = dao.boardImageInsert(img); // 새로운 이미지 삽입
                            
                            if(result == 0) { // 이미지 삽입 실패
                                throw new BoardUpdateException("이미지 수정/삽입 예외");
                            }
                        }
                        
                        
                    }  //if문 끝
                }      // for문 끝
                // for문이 끝나면 분류 작업 끝(분류 작업 결과 물: boardImageList, reNameList)을 이용해서
                // 파일을 서버에 저장
                if(!boardImageList.isEmpty()) {
                    
                    // 서버에 이미지 저장
                    for(int i = 0; i<boardImageList.size(); i++) {
                        int index = boardImageList.get(i).getImageOrder(); // 분류된 이미지가 순서상 몇번째 이미지인가?
                        
                        imageList.get(index).transferTo(new File(folderPath + reNameList.get(i)));//원본파일이 담겨있는
                    }
                }
                
                
                
        }
        
    
    return result; // 예외발생안하면 무조건 1나옴
}

// 검색 목록 조회
@Override
public Map<String, Object> selectBoardList(Map<String, Object> pm, int cp) {

    // 1. 검색 조건이 일치하는 게시판의 전체 게시글 수 조회(단, 삭제 제외)
    int listCount = dao.getListCount(pm);
    
    // 2. 전체 게시글 수 + cp(현재 페이지)이용해서
    // 페이징 처리 객체 생성 > vo패키지에다가
    Pagination pagination = new Pagination(listCount, cp);
    
    
    // 3. 페이징 처리 객체를 이용해서 검색 조건이 일치하는 게시글 목록 조회
    List<Board> boardList = dao.selectBoardList(pagination, pm);
    
    
    Map<String ,Object> map = new HashMap<String, Object>();
    map.put("pagination",pagination);
    map.put("boardList", boardList);
    
    return map;
    
    
}

// 이미지 목록 조회
@Override
public List<String> selectImageList() {
    // TODO Auto-generated method stub
    return dao.selectImageList();
}
    
    
}
