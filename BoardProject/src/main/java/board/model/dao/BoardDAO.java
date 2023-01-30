package board.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import board.model.vo.Board;

public class BoardDAO {

	private Connection conn;
	private ResultSet rs;
	
	public BoardDAO() {

		try {
			String db_URL ="jdbc:oracle:thin:@localhost:1521:XE";
			String db_Id = "board_user";
			String db_Pw = "board1234";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(db_URL, db_Id, db_Pw);

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 작성일자 메소드?
	public String getDate() {
		String sql = "SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD') FROM DUAL";
			
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs =pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	// 게시글 번호 부여 메소드
	public int getNext() {
		String sql = "SELECT BOARD_ID FROM BOARD ORDER BY BOARD_ID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();   // sql 문 실행
			if(rs.next()) {   // 가장 상위의 게시글번호를 구해서 그번호에 +1 더한값을 새번호로 부여
				return rs.getInt(1) + 1 ;
			}
			return 1 ; //첫번째 게시물인 경우
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return -1; //  DB오류
	}
	
	// 글쓰기 메서드
	public int wrtie(String boardTitle, String userId, String boardContent) {
		String sql = "INSERT INTO BOARD VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext());
			pstmt.setString(2, boardContent);
			pstmt.setString(3, userId);
			pstmt.setString(4, getDate());
			pstmt.setString(5, boardContent);
			pstmt.setInt(6, 1); // 글의 유효번호?
			return pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return -1;// db오류
	}
	
	
	// 게시글 리스트 메서드
	public ArrayList<Board> getList(int pageNumber){
	
//		String sql = "SELECT * FROM BOARD WHERE BOARD_ID < ? AND BOARD_AVAILABLE =1 ORDER BY BOARD_ID DESC LIMIT 10";
//		String sql = "SELECT * FROM (SELECT * FROM BOARD ORDER BY BOARD_ID) WHERE ROWNUM = 1 AND BOARD_ID < ?";
		String sql = "SELECT * FROM (SELECT * FROM BOARD ORDER BY BOARD_ID) WHERE BOARD_AVAILABLE = 1 AND ROWNUM < ? ORDER BY BOARD_ID DESC";
		ArrayList<Board> list = new ArrayList<Board>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() - (pageNumber -1) * 10);  // >> 들어갈데가없어서 오류
			// 모든 게시글 조회 + 유효번호 1인것 , 게시글 다음번호
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setBoardId(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));
				board.setUserId(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setBoardContent(rs.getString(5));
				board.setBoardAvailable(rs.getInt(6));
				list.add(board);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	// 페이징 처리 메소드
	public boolean nextPage(int pageNumber) {
		String sql = "SELECT * FROM BOARD WHERE BOARD_ID < ? AND BOARD_AVAILABLE =1";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getNext() -(pageNumber -1) * 10);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 하나의 게시글 보는 메소드
	public Board getBoard(int boardId) {
		String sql = "SELECT * FROM BOARD WHERE BOARD_ID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Board board = new Board();
				board.setBoardId(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));
				board.setUserId(rs.getString(3));
				board.setBoardDate(rs.getString(4));
				board.setBoardContent(rs.getString(5));
				board.setBoardAvailable(rs.getInt(6));
				return board;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	// 게시글 수정 메서드
	public int update(int boardId, String boardTitle, String boardContent) {
		String sql = "UPDATE BOARD SET BOARD_TITLE = ?, BOARD_CONTENT = ? WHERE BOARD_ID = ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardId);
			
			return pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return -1; // DB 오류
	}
	
	// 게시글 삭제 메서드  게시글 유효번호를 0으로 바꾼다(update)
	public int delete(int boardId) {
		String sql = "UPDATE BOARD SET BOARD_AVAILABLE = 0 WHERE BOARD_ID =?";
		try {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardId);
		
		return pstmt.executeUpdate();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return -1;
	}
	
	
	
	
	
}
