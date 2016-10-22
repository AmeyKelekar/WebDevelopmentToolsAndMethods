package com.amey.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amey.spring.dao.AnnouncementDAO;
import com.amey.spring.dao.DAO;

@Controller
@RequestMapping("/managerAnnouncement.htm")
public class ManagerAnnouncementController {
	@Autowired
	AnnouncementDAO announcementDAO = new AnnouncementDAO();
	@SuppressWarnings("rawtypes")
	List arrayList = new ArrayList();
	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView init(HttpServletRequest request, HttpServletResponse response) throws Exception {
		arrayList = announcementDAO.list();
		DAO.close();
		return new ModelAndView("listAnnouncement", "arrayList", arrayList);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("addAnnouncement");
	}
}
