package com.javaex.vo;

public class GuestbookVo {

	
	//필드
	private int no;
	private String name;
	private String password;
	private String contents;
	private String date;
	
	
	//생성자
	public GuestbookVo() {
		
	}

	public GuestbookVo(int no, String name, String password, String contents, String date) {
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.contents = contents;
		this.date = date;
	}

	//gs
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	//일반
	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", password=" + password + ", contents=" + contents
				+ ", date=" + date + "]";
	}
	
	
	
	
	
	
	

}
