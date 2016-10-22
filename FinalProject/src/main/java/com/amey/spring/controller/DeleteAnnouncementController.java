package com.amey.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.AnnouncementDAO;
import com.amey.spring.dao.DAO;
import com.amey.spring.pojo.Announcement;

@Controller
@RequestMapping("/deleteAnnouncement.htm")
public class DeleteAnnouncementController {
	@Autowired
	AnnouncementDAO announcementDAO = new AnnouncementDAO();
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView doGet(HttpServletRequest request,HttpServletResponse response) throws Exception {		
		return doPost(request, response);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		long id = Integer.parseInt(request.getParameter("id"));
		Announcement announcement = announcementDAO.getByID(id);
		if(announcement != null){
			announcementDAO.delete(announcement);
			DAO.close();
			return new ModelAndView("managerSuccess");
		}
		else{
			DAO.close();
			return new ModelAndView("managerError");
		}
	}
}
