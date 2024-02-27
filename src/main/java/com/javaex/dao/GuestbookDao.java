package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			query += " delete from guest ";
			query += " where no = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제되었습니다.");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}
		// 수정
		public int guestUpdate(GuestVo guestVo) {
			int count = -1;//기본적으로 수정된 행이 없음을 나타내는 값

			this.getConnection();
			
			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				// SQL문 준비
				String query = "";
				query += " update guest ";
				query += " set name=?, ";
				query += " 	   password=?, ";
				query += "     contents=? ";
				query += "     date=? ";
				query += " where no = ? ";

				// 바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, guestVo.getName());
				pstmt.setString(2, guestVo.getPassword());
				pstmt.setString(3, guestVo.getContents());
				pstmt.setInt(4, guestVo.getDate());
				pstmt.setInt(5, guestVo.getNo());
				
				// 실행
				count = pstmt.executeUpdate();

				// 4.결과처리
				System.out.println(count + "건 수정되었습니다.");

			} catch (SQLException e) {
				System.out.println("error:" + e);
			} 
			
			this.close();

			return count;// Controller에서 guestUpdate 메소드를 호출하고, 이 메소드가 반환하는값(count)을 처리할 것
		}
		
		// 1개 가져오기
		public GuestVo guestSelectOne(int no) {

			this.getConnection();

			GuestVo guestVo = null;

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				// SQL문 준비
				String query = "";
				query += " select no, ";
				query += "	      name, ";
				query += "        password, ";
				query += "	      contents, ";
				query += "	      date ";
				query += " from guest ";
				query += " where no=? ";

				// 바인딩
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, no);

				// 실행
				rs = pstmt.executeQuery();

				// 4.결과처리
				while (rs.next()) {// 반복
					int personId = rs.getInt("no");
					String name = rs.getString("name");
					String pw = rs.getString("password");
					String contents = rs.getString("contents");
					String date = rs.getString("date");

					// db에서 가져온 데이터 vo로 묶기
					guestVo = new GuestVo(personId, name, pw, contents, date);

				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			this.close();

			return guestVo;
		}
		// 전체가져오기
		public List<GuestVo> guestSelect() {

			this.getConnection();

			List<GuestVo> guestList = new ArrayList<GuestVo>();

			try {
				// 3. SQL문 준비 / 바인딩 / 실행
				// SQL문 준비
				String query = "";
				query += " select no, ";
				query += "	      name, ";
				query += "        password, ";
				query += "	      contents, ";
				query += "	      date ";
				query += " from guest ";

				// 바인딩
				pstmt = conn.prepareStatement(query);

				// 실행
				rs = pstmt.executeQuery();

				// 4.결과처리
				while (rs.next()) {// 반복
					int personId = rs.getInt("no");
					String name = rs.getString("name");
					String pw = rs.getString("password");
					String contents = rs.getString("contents");
					String date = rs.getString("date");
					
					// db에서 가져온 데이터 vo로 묶기
					GuestVo guestVo = new GuestVo(personId, name, pw, contents, date);
					// 리스트에 주소 추가
					guestList.add(guestVo);
				}

			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

			this.close();

			return guestList;
		}

}
