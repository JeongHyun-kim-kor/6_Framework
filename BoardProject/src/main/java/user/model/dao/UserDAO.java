package user.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	private Connection conn;     // 자바와 DB연결
	private PreparedStatement pstmt; // 쿼리문
	private ResultSet rs;	// 결과값 받아오는
	
	public UserDAO() {
	
		try {
			String db_URL ="jdbc:oracle:thin:@localhost:1521:XE";
			String db_Id = "board_user";
			String db_Pw = "board1234";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("오라클 드라이버 로딩 ");
			
			Connection conn = DriverManager.getConnection(db_URL, db_Id, db_Pw);
			System.out.println("DB접속성공 ");

			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


// 로그인 영역
	public int login(String userId, String userPassword) {
		String sql = "SELECT USER_PW FROM USER WHERE USER_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(pstmt);
			pstmt.setString(1, userId);
			rs= pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return  1; //로그인 성공
				} else
					return  0; //비밀번호 틀림
			}
			return -1 ; // 아이디 없음
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -2 ; // DB 에러
		
	}

}