package com.javaex.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.vo.GuestbookVo;

public class GuestbookController {
	// 필드

	// 생성자

	// 메소드gs

	// 일반
	@RequestMapping(value="/phone/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("/delete");
		System.out.println(guestbookVo.toString());
	
		guestbookDao.delete(guestbookVo);
	
	return "redirect:/guest/addList";
	}

	@RequestMapping (value="/delete", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("/delete2");
		System.out.println(guestbookVo.toString());
	
	int count = guestbookDao.delete(guestbookVo);
	if(count==1) {
	return "redirect:/guest/addList";
	
	}else{ 
		return "redirect:/guest/deleteForm?no=" + guestbookVo.getNo();
	}
	

}
