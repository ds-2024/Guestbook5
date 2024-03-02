package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	//필드
	@Autowired
	private GuestbookDao guestbookDao; //Dao 자동 연결
	//생성자
	//메소드 gs
	//메소드 일반
	
	//등록
	public void exeWrite(GuestbookVo guestbookVo) {
		System.out.println("GuestbookService.exeWrite()");
		
		GuestbookDao guestbookDao = new GuestbookDao();
		guestbookDao.guestInsert(guestbookVo);
	}
	//수정폼
	public GuestbookVo exeModifyForm(int no) {
		System.out.println("GuestbookService.exeModifyForm()");
		
		GuestbookVo guestbookVo = guestbookDao.guestSelectOne(no);
		return guestbookVo;
	//수정
		public int exeModify(GuestbookVo guestbookVo) {
			System.out.println("GuestbookService.exeModify()");
			
			int count = guestbookDao.guestUpdate(guestbookVo);
			System.out.println(guestbookVo);
			return count;
			
		
	}
	
}
