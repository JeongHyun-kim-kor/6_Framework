package board.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import oracle.jdbc.babelfish.BabelfishConnection;

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
		String sql = "SELECT TO_CHAR(SYSDATE,'YYYY-MM-DD') FROM DUAL"
				+ "";
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
}
