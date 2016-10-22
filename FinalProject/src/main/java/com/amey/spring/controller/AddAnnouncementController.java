package com.amey.spring.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.AnnouncementDAO;
import com.amey.spring.dao.DAO;
import com.amey.spring.pojo.Announcement;
import com.amey.spring.pojo.User;

@Controller
@RequestMapping("/addAnnouncement.htm")
public class AddAnnouncementController {
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return doPost(request,response);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Announcement announcement = new Announcement();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Date date=new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		
		announcement.setDate(simpleDateFormat.format(date));
		announcement.setContent(request.getParameter("content"));
		announcement.setPostby(user.getName());
		
		AnnouncementDAO announcementDAO = new AnnouncementDAO();
		announcementDAO.create(announcement);
		
		DAO.close();
		return new ModelAndView("managerSuccess");
	}
}
