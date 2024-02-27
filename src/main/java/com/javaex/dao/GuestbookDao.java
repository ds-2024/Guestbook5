package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.GuestVo;

public class GuestbookDao {
	
	// 필드
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rs = null;
		private String driver = "com.mysql.cj.jdbc.Driver";
		private String url = "jdbc:mysql://localhost:3306/guest_db";
		private String id = "guest";
		private String pw = "guest";
	// 생성자
	// 메소드-gs

	// 메소드-일반

	// 연결
	public void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);

		} catch (SQLException e) {
			System.out.println("error:" + e);

		}
	}

	// 종료
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 등록
		public int guestInsert(GuestVo guestVo) {
			int count = -1;

			this.getConnection();
			
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				// SQL문 준비
				String query = "";
				query += " insert into guest ";
				query += " values(null, ?, ?, ?, ? ) ";

				// 바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, guestVo.getName());
				pstmt.setString(2, guestVo.getPassword());
				pstmt.setString(3, guestVo.getContents());
				pstmt.setString(4, guestVo.getDate());

				// 실행
				count = pstmt.executeUpdate();

				// 4.결과처리
				System.out.println(count + "건 등록되었습니다.");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			this.close();

			return count;
		}

		
	//삭제
	public int guestDelete(int no) {
		int count = -1;

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			// SQL문 준비
			String query = "";
			query += "  "
	}
	}

}
